DIRS = Thesis Defense Data

BASE_ARCHIVE = $(HOME)/Backup/Masters
CURRENT_ARCHIVE = $(BASE_ARCHIVE)/current
REMOTE_BASE = /home/mike/ThesisBackup
ARCHIVE_TEMPLATE = xxx-$(shell date +20%y.%m.%d.%H%M%S).tar.gz

SCP = scp -C
RSYNC = rsync -av -e "ssh -C" 

default:

all-backup: $(DIRS:%=backup-%) sync
	echo $^

sync: all-clean
	$(RSYNC) --delete ./ mike@smoke.dyndns.org:Workspaces/MastersThesis

retrieve:
	$(RSYNC) mike@smoke.dyndns.org:Workspaces/MastersThesis/ .

all-clean: clean $(DIRS:%=clean-%)

clean:
	find . -name "*~" -exec rm '{}' ';'

### It's 3am, why am I making this generic?
backup-%: clean-% archive-%
	echo Done backing up $*

clean-%:
	$(MAKE) -C $* -f Makefile clean

archive-%: ARCHIVE = $(subst xxx,$*,$(ARCHIVE_TEMPLATE))
archive-%: REMOTE_ARCHIVE = $(REMOTE_BASE)/$(ARCHIVE)
archive-%:
	#tar czf $(ARCHIVE) $*
	#cp $(ARCHIVE) $(CURRENT_ARCHIVE)/$*-current.tar.gz
	#$(SCP) $(ARCHIVE) mike@66.178.204.99:ThesisBackup
	tar czf - $* | ssh mike@smoke.dyndns.org "dd of=$(REMOTE_ARCHIVE)"

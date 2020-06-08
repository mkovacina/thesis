TEX_BASE = ~/Documents/MasterThesis/System/TeX
TEX_INPUTS = $(TEX_BASE)/prosper:$(TEX_BASE)/HA-prosper/Run:$(TEX_BASE)/xkeyval/run

all:
	#TEXINPUTS=$(TEX_INPUTS): latex defense.tex
	#TEXINPUTS=$(TEX_INPUTS): latex defense.tex
	latex defense.tex
	latex defense.tex
	dvips -t a4 -Ppdf -G0 defense.dvi -o defense.ps
	ps2pdf -dPDFsettings=/prepress defense.ps defense.pdf

clean:
	-rm -f defense.pdf defense.ps defense.out texput.log defense.log defense.aux defense.dvi *~

backup:
	$(MAKE) -C .. -f Makefile backup-defense

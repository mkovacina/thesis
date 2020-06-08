installProsper: getProsper
	sudo cp -r prosper /usr/share/texmf/tex/latex/misc
	sudo mktexlr

getProsper:
	cvs -d:pserver:anonymous@cvs.sourceforge.net:/cvsroot/prosper login
	cvs -z3 -d:pserver:anonymous@cvs.sourceforge.net:/cvsroot/prosper co -P prosper

SOURCES = main.tex 

LATEX = pdflatex

all:
	$(LATEX) main

warning:
	$(LATEX) main | grep Warning

twice:
	$(LATEX) main
	$(LATEX) main

full:
	$(LATEX) main
	bibtex main
	$(LATEX) main
	$(LATEX) main

viewer:
	xdvi -s 0  main &

pdf: all
	dvipdfm -p letter main.dvi

spell:
	ispell -t *.tex

sync:
	$(MAKE) -C .. -f Makefile sync

backup:
	$(MAKE) -C .. -f Makefile backup-Thesis

clean:
	-rm -f *.aux
	-rm -f *.log
	-rm -f *.dvi
	-rm -f *~
	-rm -f *.bak
	-rm -r *.toc
	-rm -r *.lot
	-rm -r *.log
	-rm -r *.lof
	-rm -f main.pdf main.bbl main.blg appendix.tex
	-rm -f make.out


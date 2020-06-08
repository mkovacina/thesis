default:

images:
	matlab -r "genAll('all')"

copy:
	cp Object*.eps ../Images

copy-old:
	cp od_best.eps ../Thesis/Figures/ObjectDestructionBest.eps
	cp od_mean.eps ../Thesis/Figures/ObjectDestructionMean.eps
	cp oc_best.eps ../Thesis/Figures/ObjectCollectionBest.eps
	cp oc_mean.eps ../Thesis/Figures/ObjectCollectionMean.eps
	cp om_best-1.eps ../Thesis/Figures/ObjectManipulationBest.eps
	cp om_mean-1.eps ../Thesis/Figures/ObjectManipulationMean.eps
	cp om_best-2.eps ../Thesis/Figures/ObjectManipulationBest-second.eps
	cp om_mean-2.eps ../Thesis/Figures/ObjectManipulationMean-second.eps
	cp om_m1_3d.eps ../Thesis/Figures/ObjectManipulationM5-second.eps
	cp om_m3_3d.eps ../Thesis/Figures/ObjectManipulationM7-second.eps
	cp om_sum.eps ../Thesis/Figures/ObjectManipulationSum.eps

eps2pdf:
	cd ../Images && ls Object*.eps | xargs -t -n 1 epstopdf

clean:
	-rm -f Object*.eps

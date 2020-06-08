load 'ga-data.dat';

x=reshape(ga_data(:,1),21,111);
x=x/max(max(x));

newplot;

hold on

plot(  min(x), ':o');
plot( mean(x), ':x');

legend('Best','Average');

xlabel('Generations');
ylabel('Normalized Fitness');
title('Best and average fitness score for Dispersion');

hold off

exportfig(gcf,'DispersionFitness.eps', 'color', 'cmyk');

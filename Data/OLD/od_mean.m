function od_mean

datafile = 'runs/d02/ga-data.dat';
n_gen = 84;

[x, best, gens] = crunch(...
    datafile,...
    73,...
    n_gen,...
    [3 4 5]);

mP = 1 - mean( gens(:,:,1) )/30;
mD = 1 - mean( gens(:,:,2) )/30;
mT = mean( gens(:,:,3) )/-399;

mP = mP';
mD = mD';
mT = mT';

title('Mean Error for Object Destruction');
xlabel('Generation');
ylabel('Normalized Error');
axis([ 1 n_gen+1 0 1.1 ]);

hold on;

plot( mT, 'rd:' )
plot( mP, 'bp:' );
plot( mD, 'gx:' );

legend( 't', 'd_2', 'd_1' );

hold off;

exportfig(gcf,'od_mean.eps', 'color', 'cmyk' );


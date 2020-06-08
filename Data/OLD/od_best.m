function od_best

n_gen = 84;

datafile = 'runs/d01/ga-data.dat';

[x, best, gens] = crunch(datafile,73,n_gen,[3 4 5]);
nbest = best./repmat([30 30 -399],n_gen,1);
nbest(:,1:2) = 1-nbest(:,1:2);

nbest = [ 1 1 1; nbest ];

newplot;

%%% [3] Define decorations and plot area
title('Best fitness score for Object Destruction');
xlabel('Generation');
ylabel('Normalized Error');
axis( [ 1 n_gen+1 0 1.1 ] );

%%% [4] Plot data
hold on;
plot( nbest(:,3), 'rd:' );
plot( nbest(:,2), 'bp:' );
plot( nbest(:,1), 'gx:' );

legend( 't', 'd_2', 'd_1' );

hold off;

exportfig(gcf,'od_best.eps', 'color', 'cmyk' );


function om_best

n_gen = 91;
p.n_gen = n_gen;
p.datafile = 'runs/m01/ga-data.dat';
p.n_soln = 73;
p.cols = [7 8 9 10 11 3 4 5 6];
[x, best, gens] = crunch(p);
%     [x, best, gens] = crunch(...
%     'runs/m01/ga-data.dat', ...%'m[2005-03-05]-10.50.19/ga-data.dat'...
%     73,n_gen,[7 8 9 10 11 3 4 5 6]);
nbest=best;
nbest(:,5) = nbest(:,5)/-399;
nbest(:,5) = 1-nbest(:,5);
nbest = 1 - nbest;

title('Best fitness score for Object Manipulation');
xlabel('Generation');
ylabel('Normalized Error');
axis( [ 1 n_gen 0 1.1 ] );

hold on;

plot( nbest(:,5), 'r' );
plot( nbest(:,4), 'g' );
plot( nbest(:,3), 'b' );
plot( nbest(:,2), 'm' );
plot( nbest(:,1), 'y' );

legend( 't', 'm_8', 'm_7', 'm_6', 'm_5' );

hold off;

exportfig(gcf,'om_best.eps', 'color', 'cmyk' );

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clf

m5 = mean( gens(:,:,1) );
m6 = mean( gens(:,:,2) );
m7 = mean( gens(:,:,3) );
m8 = mean( gens(:,:,4) );

m5 = 1-m5';
m6 = 1-m6';
m7 = 1-m7';
m8 = 1-m8';

title('Mean error for Object Manipulation');
xlabel('Generation');
ylabel('Normalized Error');
axis( [ 1 n_gen 0 1.1 ] );

hold on;

plot( m5, 'g' );
plot( m6, 'm' );
plot( m7, 'b' );
plot( m8, 'y' );

legend( 'm_5', 'm_6', 'm_7', 'm_8' );

hold off;

exportfig(gcf,'om_mean.eps', 'color', 'cmyk' );

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

clf 

m1 = sum( gens( :, :, 1 ) );
m2 = sum( gens( :, :, 2 ) );
m3 = sum( gens( :, :, 3 ) );
m4 = sum( gens( :, :, 4 ) );


m1 = m1';
m2 = m2';
m3 = m3';
m4 = m4';

title('Number of solutions satisfying some criteria for Obect Manipulation');
xlabel('Generation');
ylabel('Normalized Performance');

axis( [ 1 n_gen 0 max([m1; m2; m3; m4]) ]);

hold on;
plot( m1, 'g' );
plot( m2, 'm' );
plot( m3, 'b' );
plot( m4, 'y' );

legend( 'm_1', 'm_2', 'm_3', 'm_4' );

hold off

exportfig(gcf,'om_sum.eps', 'color', 'cmyk' );



function oc_mean(datafile)

p.datafile = datafile;
p.n_gen = 357;
p.n_soln = 73;
p.vec = [ 3 4 5 ];
p.max = [ 50 50 -399 ];
p.labels = { 'c_1', 'c_2', 't' };
p.title = 'Object Collection';
p.pictfile = 'oc_mean.eps';

[x, best, gens] = crunch(...
    p.datafile, ...
    p.n_soln, ...
    p.n_gen, ...
    p.vec );

% mC = 1-mean( gens(:,:,1) )/50;
% mL = 1-mean( gens(:,:,2) )/50;
% mT = mean( gens(:,:,3) )/-399;
% 
% mC = mC';
% mL = mL';
% mT = mT';

m = mean(gens);
m = reshape(m,p.n_gen,3); % because mean return size(m) = [1 3 3]
m = m ./ repmat( p.max, p.n_gen, 1 ) 
m(:,1:2) = 1 - m(:,1:2); % convert to error measure

title( ['Mean Error for ' p.title ] );
xlabel('Generation');
ylabel('Normalized Error');
axis([ 1 p.n_gen 0 1.1 ]);

hold on;

plot( m, 'd:' );
% plot( , 'rd:' )
% plot( mL, 'bp:' );
% plot( mC, 'gx:' );

legend( deal(p.labels) );

hold off;

exportfig(gcf, p.pictfile, 'color', 'cmyk' );

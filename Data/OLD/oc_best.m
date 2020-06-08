function oc_best( datafile )

p.datafile = 'runs/c01/ga-data.dat';
p.n_gen = 357;
p.n_soln = 73;
p.max = [ 50 50 -399];
p.vecs = [3 4 5];
p.labels = { 't', 'c_2', 'c_1' };
p.title = 'Object Collection';
p.pictfile = 'oc_best.eps';

genBest(p);

% [x, best, gens] = crunch( ...
%     datafile, ...
%     p.n_soln, ...
%     p.n_gen, ...
%     p.vecs);
% nbest=best./repmat( p.max, p.n_gen, 1);
% nbest(:,1:2) = 1-nbest(:,1:2);
% 
% nbest = [ 1 1 1; nbest ];
% 
% newplot;
% 
% title( strcat('Best fitness score for', p.title ) );
% xlabel('Generation');
% ylabel('Normalized Error');
% axis( [ 1 p.n_gen 0 1.1 ] ) ;
% 
% %%% [4] Plot data
% hold on;
% plot( nbest(:,3), 'rd:' );
% plot( nbest(:,2), 'bp:' );
% plot( nbest(:,1), 'gx:' );
% 
% legend( deal(p.labels) );
% 
% hold off;
% 
% exportfig(gcf, p.pictfile, 'color', 'cmyk' );


function genAll( dataset )
%
% xxx

switch dataset
    case 'c'
        genCollection;
    case 'd'
        genDestruction;
    case 'm'
        genManipulation;
    case 'all'
        genCollection;
        genDestruction;
        genManipulation;
        genManipulation2;
    otherwise
        error('What are you thinking....');
end

%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%
function genCollection

p.datafile = 'runs/c01/ga-data.dat';
p.n_metrics = 3;
p.n_gen = 357;
p.n_soln = 73;
p.revcols=[ 1 2 ];
p.max = [ 50 50 -399];
p.cols = [3 4 5];
p.labels = { 'c_1', 'c_2', 't' };

[x, p.best, p.gens] = crunch(p);

%[1] oc_best
p.title = [ 'Best fitness for ' 'Object Collection' ];
p.pictfile = 'ObjectCollectionBest.eps';
data = calcBest(p);
% reverse time so that it is growing to 1, not 0
data(:,3) = 1 - data(:,3);
genPlot(p,data);

%[2] oc_mean
p.title = [ 'Mean fitness for ' 'Object Collection' ];
p.pictfile = 'ObjectCollectionMean.eps';
data = calcMean(p);
% reverse time so that it is growing to 1, not 0
data(:,3) = 1 - data(:,3);
genPlot(p,data);

%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%
function genDestruction

p.datafile = 'runs/d01/ga-data.dat';
p.n_metrics = 3;
p.n_gen = 50;
p.n_soln = 73;
p.revcols = [1 2];
p.max = [ 30 30 -399];
p.cols = [3 4 5];
p.labels = { 'd_1', 'd_2', 't' };

[x, p.best, p.gens] = crunch(p);

p.title = [ 'Best fitness for ' 'Object Destruction' ];
p.pictfile = 'ObjectDestructionBest.eps';
data = calcBest(p);
% reverse time so that it is growing to 1, not 0
data(:,3) = 1 - data(:,3);genPlot(p,data);

p.title = [ 'Mean fitness for ' 'Object Destruction' ];
p.pictfile = 'ObjectDestructionMean.eps';
data = calcMean(p);
% reverse time so that it is growing to 1, not 0
data(:,3) = 1 - data(:,3);
genPlot(p, data);

%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%
function genManipulation

p.datafile = 'runs/m02/ga-data.dat';
p.n_metrics = 5;
p.n_gen = 427;
p.n_soln = 73;
p.revcols = [1 2 3 4];
p.max = [ 1 1 1 1 -399];
p.cols = [7 8 9 10 11];
p.labels = { 'm_5', 'm_6', 'm_7', 'm_8', 't' };

[x, p.best, p.gens] = crunch(p);

p.title = [ 'Best fitness for ' 'Object Manipulation' ];
p.pictfile = 'ObjectManipulationBest-2.eps';
data = calcBest(p);
% reverse time so that it is growing to 1, not 0
data(:,5) = 1 - data(:,5);
genPlot(p,data);

p.title = [ 'Mean fitness for ' 'Object Manipulation' ];
p.pictfile = 'ObjectManipulationMean-2.eps';
data = calcMean(p);
% reverse time so that it is growing to 1, not 0
data(:,5) = 1 - data(:,5);
genPlot(p,data);

% Additional plot for OM
% m1 = sum( gens( :, :, 1 ) );
% m2 = sum( gens( :, :, 2 ) );
% m3 = sum( gens( :, :, 3 ) );
% m4 = sum( gens( :, :, 4 ) );
m = sum(p.gens(:,:,1:4));
m = reshape(m,p.n_gen,4);

axis( [ 1 p.n_gen 0 max(max(m)) ]);
clf 
hold on;
plot(m(:,1),'g:o');
plot(m(:,2),'m:x');
plot(m(:,3),'b:*');
plot(m(:,4),'y:+');

legend( 'm_1', 'm_2', 'm_3', 'm_4', 'Location', 'BestOutside' );

title('Number of solutions satisfying some criteria for Obect Manipulation');

xlabel('Number of Satisfying Solutions');
ylabel('Normalized Performance');

hold off

% exportfig(gcf,'om_sum.eps', 'color', 'cmyk' );
exportfig(gcf,'ObjectManipulationSum-2.eps', 'color', 'cmyk' );


newplot
hold on
% imagesc(1-sort( p.gens(:,:,1) ));
imagesc(sort( p.gens(:,:,1) ));
title('Distribution of metric m_5 scores over each generation');
xlabel('Generation');
ylabel('Solution');
colorbar
hold off
% exportfig(gcf,'om_m1_3d.eps', 'color', 'cmyk' );
exportfig(gcf,'ObjectManipulationM5-2.eps', 'color', 'cmyk' );

newplot
hold on
% imagesc(1-sort( p.gens(:,:,3) ) );
imagesc(sort( p.gens(:,:,3) ) );
title('Distribution of metric m_7 scores over each generation');
xlabel('Generation');
ylabel('Solution');
colorbar
hold off
% exportfig(gcf,'om_m3_3d.eps', 'color', 'cmyk' );
exportfig(gcf,'ObjectManipulationM7-2.eps', 'color', 'cmyk' );


%%%%%%%%%%%%%%%%%%%%%%%%
%%%%%%%%%%%%%%%%%%%%%%%%
function genManipulation2

p.datafile = 'runs/m01/ga-data.dat';
p.n_metrics = 5;
p.n_gen = 366;
p.n_soln = 73;
p.revcols = [1 2 3 4];
p.max = [ 1 1 1 1 -399];
p.cols = [7 8 9 10 11];
p.labels = { 'm_5', 'm_6', 'm_7', 'm_8', 't' };

[x, p.best, p.gens] = crunch(p);

p.title = [ 'Best fitness for ' 'Object Manipulation' ];
p.pictfile = 'ObjectManipulationBest-1.eps';
data = calcBest(p);
% reverse time so that it is growing to 1, not 0
data(:,5) = 1 - data(:,5);
genPlot(p,data);

p.title = [ 'Mean fitness for ' 'Object Manipulation' ];
p.pictfile = 'ObjectManipulationMean-1.eps';
data = calcMean(p);
% reverse time so that it is growing to 1, not 0
data(:,5) = 1 - data(:,5);
genPlot(p,data);

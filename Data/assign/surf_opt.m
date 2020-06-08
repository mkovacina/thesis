function avg = surf_opt(func)

switch func
case 'auction'
   algorithm = 'opt_assignment_auction';
case 'random'
   algorithm = 'opt_assignment_random';
case 'ga'
   algorithm = 'opt_assignment_ga';
otherwise
   error('[ERROR] invalid algorithm selected');
end

%% -----------------------------------
size_Generations = 20;
size_Problems    = 10;
n_Iterations     = 100;

results = zeros(size_Problems,size_Generations);
total = zeros(1,n_Iterations);

for t = 1:size_Problems
   costTable = floor( rand( t*10 )*100 ) + 1;
   [optCost, optAssignment] = assignment_hungarian(costTable);
   
   for g=1:size_Generations
      fprintf('%s: %d %d\n', func, t*10, g*100 );
      
      for i=1:n_Iterations
         [c,a] = feval(algorithm, costTable, g*100 );
         total(i) = c;
      end
      
      results( t, g ) = sum( total - optCost )/n_Iterations;
   end
end

assignin('caller','results', results);

newplot;
hold on;
xlabel('Number of Generations');
ylabel('Size of Assignment');
title('Percentage Optimal');
surf( 1:size_Generations, 1:size_Problems, results );
hold off;
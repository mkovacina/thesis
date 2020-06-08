function avg = avg_iter_opt(func, costTable, n_Iterations, max_Iterations)

[optCost, optAssignment] = assignment_hungarian(costTable);

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

total = 0;

for i=1:n_Iterations
   fprintf('%s: %d\n',func,i);
   [c,a,iter] = feval(algorithm, costTable, optCost, max_Iterations);
   total = total + iter;
end

avg = total / n_Iterations;
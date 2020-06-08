function avg = avg_percent_opt(func,costTable,n_Iterations, max_Iterations)

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


total = zeros(1,n_Iterations);
for i=1:n_Iterations
   fprintf('%s: %d\n',func,i);
   [c,a] = feval(algorithm, costTable, max_Iterations);
   total(i) = c;
end

avg = sum( total - optCost )/n_Iterations;
sum(total)
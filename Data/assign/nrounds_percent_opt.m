function [avg] = nrounds_percent_opt(func,costTable,n_Iterations)

[optCost, optAssignment] = assignment_hungarian(costTable);

switch func
case 'auction'
   algorithm = 'assignment_auction';
case 'random'
   algorithm = 'assignment_random';
case 'ga'
   algorithm = 'assignment_ga';
otherwise
   error('[ERROR] invalid algorithm selected');
end

%% -----------------------------------
iterations = [ 100:50:1000 ];
s=size(iterations);

avg = zeros(s(2),2);
avg(:,2) = iterations';

total = zeros(1,n_Iterations);

for i=1:s(2)
   fprintf('%s: %d -> %d\n',func,i,iterations(i));
   
   for j=1:n_Iterations
      j
      [c,a] = feval(algorithm,costTable,iterations(i));
      total(j) = c;
   end

   avg(i,1) = sum( total - optCost )/n_Iterations;
end
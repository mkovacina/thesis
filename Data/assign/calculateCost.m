function cost = calculateCost( assignment, costTable )

s = size(assignment);
numTasks = s(2);

cost = 0;

for i=1:numTasks
   cost = cost + costTable( assignment(i), i );
end

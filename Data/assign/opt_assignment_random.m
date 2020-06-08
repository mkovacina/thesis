function [bestCost, bestAssignment, iter] = opt_assignment_random( costTable, max_Iterations )

[optCost, optAssignment] = assignment_hungarian(costTable);


s = size(costTable);
numAgents = s(1);
numTasks  = s(2);

if ( numAgents ~= numTasks )
   error('number of agents must equal number of tasks');
end

bestAssignment = randperm(numTasks);
bestCost = calculateCost( bestAssignment, costTable );
iter = 0;

while( bestCost > optCost & iter < max_Iterations )
   iter = iter + 1;
   
   assignment = randPerm(numTasks);
   cost = calculateCost( assignment, costTable );
   
   if ( cost < bestCost )
      bestAssignment = assignment;
      bestCost = cost;
   end
end

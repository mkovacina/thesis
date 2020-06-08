function [bestCost, bestAssignment] = assignment_random( costTable, n_Iterations )

s = size(costTable);
numAgents = s(1);
numTasks  = s(2);

if ( numAgents ~= numTasks )
   error('number of agents must equal number of tasks');
end

%% ------- Statistical
[optCost,optAssignment] = assignment_hungarian(costTable);
best_history = zeros(n_Iterations,1);
diff_history = zeros(n_Iterations,1);
%% -------

bestAssignment = randperm(numTasks);
bestCost = calculateCost( bestAssignment, costTable );

for i=1:n_Iterations
   assignment = randPerm(numTasks);
   cost = calculateCost( assignment, costTable );
   
   if ( cost < bestCost )
      %% ------- Statistical
      diff_history(i:end) = bestCost - cost;
      %% -------------------
      
      bestAssignment = assignment;
      bestCost = cost;
   end
   
   %% ------- Statistical
   best_history(i) = bestCost;
   %% -------------------   
end


%% ------ plotting
newplot;
title('Random');
hold on;
plot(best_history,'r');
plot(diff_history,'b');
hold off;
assignin('caller','best_history', best_history);
%% ---------------

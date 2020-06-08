function [ minCost, optDecision, iter ] = opt_assignment_auction( costTable, max_Iterations )
% AUCTION auction based task allocation tool
%  Arguements:
%    -- costTable:     a two-dimensional cost matrix.  rows => agents, cols => tasks
%    -- optCost:       the target cost
%
% Outputs
%   -- minCost:     the cost of the best assignment
%   -- optDecision: the best decision vector
%   -- iter:        the number of iterations to find the best solution

[optCost, optAssignment] = assignment_hungarian(costTable);


% find the size of the cost matrix
sc = size(costTable);

% rows = agent costs
% cols = tasks
n_Agents = sc(1);
n_Tasks  = sc(2);

% sorted holds task selection preference for each agent, sorted lowest-to-hightest cost
sorted = zeros( n_Agents, n_Tasks );

% sorts each agents tasks 
for i=1:n_Agents
   %% sort returns the sorted vector and the sorted array of indexs
   %% the array of indes correspond to task numbers, and thus are
   %% stored in sorted
   [t,sorted(i,:)] = sort( costTable(i,:) );   
end

%% generate a random decision and find it's cost
optDecision = randPerm(n_Tasks);
minCost = calculateCost( optDecision, costTable );
iter = 0;

while( minCost > optCost & iter < max_Iterations )
   iter = iter + 1;
   
   % binary vector denoting which tasks have been chosen
   chosen = zeros( 1, n_Tasks );
   
   % the current decision vector
   currDecision = zeros( 1, n_Tasks );
   
   % the current cost
   currCost = 0;
   
   % generate a random bid order without repetition
   bidOrder = randperm( n_Agents );
   
   % cycle through the bid order
   for agent=1:n_Tasks
      
      % select the current bidding agent (by ID) from the bidding order
      agent_id = bidOrder(agent);
      
      % cycle through the tasks to chose the least costing of the remaining
      for j=1:n_Tasks
         % select the agents jth favorite choice
         choice = sorted( agent_id, j );
         
         % check if the task has been previous selected and
         % if the cost is negative, then do not chose it
         if chosen( choice ) == 0 & costTable( agent_id, choice ) >= 0  %% modified 05-15-2002
            % assign the task, update the current decision vector and cost
            chosen( choice ) = 1;
            currDecision( choice ) = agent_id;
            currCost = currCost + costTable( agent_id, choice );
            break;
         end
      end
   end
   
   % is the current solution better than the previous one and also
   % verify that this is a complete solution (all tasks taken).  If
   % the solution is incomplete, discard it immediately
   if currCost < minCost & sum( chosen ) == n_Tasks %% modified 05-15-2002
      % make the current decision vector and cost the best decision vector and cost
      minCost = currCost;
      optDecision = currDecision;
   end
end
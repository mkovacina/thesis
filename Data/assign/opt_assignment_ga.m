function [minCost, bestAssignment, iter] = opt_assignment_ga( costTable, max_Iterations )

[optCost, optAssignment] = assignment_hungarian(costTable);

s = size(costTable);
n_Agents = s(1);
n_Tasks  = s(2);

%% must be even multiple of 4, else change main loop code
populationSize = 100;
sec1 = 1:populationSize/4;
sec2 = populationSize/4:populationSize/2;
sec3 = populationSize/2:populationSize*3/4;
sec4 = populationSize*3/4:populationSize;
sec2Size = populationSize/4+1;


if ( n_Tasks ~= n_Agents )
   error('number of agents must equal number of tasks');
end

population = genInitialPopulation( populationSize, n_Agents, n_Tasks );
bestAssignment = randperm(n_Tasks);
minCost = calculateCost( bestAssignment, costTable );

newPopulation = zeros( populationSize, n_Tasks );

n_RandNum = 10000;
randList = floor(rand(1,n_RandNum)*populationSize)+1;
rand_idx = 1;
iter = 0;

while( minCost > optCost & iter < max_Iterations )
   [rank, minCost, bestAssignment] = evaluatePopulation( population, costTable, minCost, bestAssignment );
   
   newPopulation(sec1,:) = population( rank(sec1,2), : );
   newPopulation(sec2,:) = genInitialPopulation(sec2Size,n_Agents,n_Tasks);
   
   for x=sec3
      newPopulation(x,:) = mutate( population(randList(rand_idx),:), costTable );
      rand_idx = rand_idx+1;
      
      if ( rand_idx >= n_RandNum-1 )
         randList = floor(rand(1,n_RandNum)*populationSize)+1;
         rand_idx = 1;
      end   
   end
   
   for x=sec4
      newPopulation(x,:) = mutate( population(randList(rand_idx),:), costTable );
      rand_idx = rand_idx+1;
      
      if ( rand_idx >= n_RandNum-1 )
         randList = floor(rand(1,n_RandNum)*populationSize)+1;
         rand_idx = 1;
      end
   end
   
   population = newPopulation;
end




%% ---- create a random initial population
function initialPopulation = genInitialPopulation( numMembers, n_Agents, n_Tasks )
%initialPopulation = floor(rand( numMembers, n_Tasks )*n_Agents )+1;
initialPopulation = zeros(numMembers,n_Tasks);
for i=1:numMembers
   initialPopulation(i,:) = randperm(n_Tasks);
end



%% ---- evaluate and rank a population
function [rank, newminCost, newBestAssignment] = evaluatePopulation( population, costTable, minCost, bestAssignment )
% first, check validity of assignment
% second, compute cost of the assignmen

s = size(population);
numMembers = s(1);
length = s(2);

rank = zeros(numMembers,2);
rank(:,1) = 0;

for i=1:numMembers
   %% store the ID of the member next to it's score for
   %% easy indexing/manipulation later
   rank(i,2) = i;
   member = population(i,:);
   
   cost = calculateCost(member, costTable);
   
   rank(i,1) = rank(i,1) + cost;
   
   if ( cost < minCost )
      minCost = cost;
      bestAssignment = member;
   end
end

newminCost = minCost;
newBestAssignment = bestAssignment;
rank = sortrows(rank);


%% ---- mutate a population member
function newMember = mutate( member, costTable )

n_Mutations = 3;

type = mod(floor(rand*10000),n_Mutations)+1;
s = size(member);
length = s(2);

switch type
case 1
   %% swap two assignments
   a = floor(rand(2,1)*length)+1;
   newMember = member;
   newMember(a(1)) = member(a(2));
   newMember(a(2)) = member(a(1));
case 2
   %% reverse a subsection
   a = floor(rand(1,2)*length)+1;
   newMember = member;
   newMember(a(1):a(2)) = newMember(a(2):-1:a(1));
case 3
   %% choose a 'best choice' for one task and replace
   a = floor(rand*length)+1;
   newMember = member;
   bestChoices = find(costTable(a,:)==min(costTable(a,:)));
   oldPos = find(member==bestChoices(1));
   newMember(oldPos) = newMember(a);
   newMember(a) = bestChoices(1);
end
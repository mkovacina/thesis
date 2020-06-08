function [bestCost, bestAssignment] = assignment_ga( costTable, n_Generations )

s = size(costTable);
n_Agents = s(1);
n_Tasks  = s(2);

mutationRate = 1;

%% must be even multiple of 4, else change main loop code
populationSize = 100;
sec1  = 1:populationSize/4;
sec2  = populationSize/4:populationSize/2;
sec3  = populationSize/2:populationSize*3/4;
sec4  = populationSize*3/4:populationSize;
sec12 = 1:populationSize/2;
sec34 = populationSize/2:populationSize;
sec2Size = populationSize/4+1;


if ( n_Tasks ~= n_Agents )
   error('number of agents must equal number of tasks');
end

population = genInitialPopulation( populationSize, n_Agents, n_Tasks );
bestAssignment = randperm(n_Tasks);
bestCost = calculateCost( bestAssignment, costTable );

%% ------- Statistical
best_history = zeros(n_Generations,1);
avg_history  = zeros(n_Generations,1);
diff_history = zeros(n_Generations,1);
%% -------

newPopulation = zeros( populationSize, n_Tasks );

n_RandNum = 10000;
randList = floor(rand(1,n_RandNum)*populationSize)+1;
rand_idx = 1;

for i=1:n_Generations
   %% ------- Statistical
   diff_history(i) = bestCost;
   %% -------
   
   [rank, bestCost, bestAssignment] = evaluatePopulation( population, costTable, bestCost, bestAssignment );
   
   %% ------- Statistical
   if ( diff_history(i) ~= bestCost )
      diff_history(i) = diff_history(i) - bestCost;
   else
      diff_history(i) = diff_history(i-1);
   end
   %% -------

   newPopulation(sec1,:) = population( rank(sec1,2), : );
   newPopulation(sec2,:) = genInitialPopulation(sec2Size,n_Agents,n_Tasks);
   
   
   newPopulation(populationSize/2:populationSize,:) = mutate( population(1:populationSize/2+1,:), mutationRate, costTable );
   
   %% ---- Statistical analysis
   best_history(i) = rank(1,1);
   avg_history(i)  = mean( rank(:,1) );
   %% -------------------------
   
   population = newPopulation;
end

%% ------ plotting
newplot;
hold on;
plot(best_history, 'r');
plot(avg_history,  'b');
plot(diff_history, 'c');
hold off;
assignin('caller','best_history', best_history);
assignin('caller','avg_history',  avg_history );
%% ---------------




%% ---- create a random initial population
function initialPopulation = genInitialPopulation( numMembers, n_Agents, n_Tasks )
%initialPopulation = floor(rand( numMembers, n_Tasks )*n_Agents )+1;
initialPopulation = zeros(numMembers,n_Tasks);
for i=1:numMembers
   initialPopulation(i,:) = randperm(n_Tasks);
end



%% ---- evaluate and rank a population
function [rank, newBestCost, newBestAssignment] = evaluatePopulation( population, costTable, bestCost, bestAssignment )
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
   
   if ( cost < bestCost )
      bestCost = cost;
      bestAssignment = member;
   end
end

newBestCost = bestCost;
newBestAssignment = bestAssignment;
rank = sortrows(rank);


%% ---- mutate a population member
function newPopulation = mutate( population, mutationRate, costTable )

sp = size(population);
n_Members = sp(2);

n_Mutations = 3;

newPopulation = population;

for i=1:n_Members
   if rand(1) < mutationRate
      member = population(i,:);
      
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
      
      newPopulation(i,:) = newMember;
   end
end
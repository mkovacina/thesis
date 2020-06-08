function timetest()

MAX = 100000;

tic;
for i=1:MAX
   floor(rand*100)+1;
end
toc

tic;
floor(rand(1,MAX)*100)+1;
toc

tic;
floor(rand(MAX,1)*100)+1;
toc

tic;
for i=1:MAX
   myRand;
end
toc

%%%%%%
function i=myRand()
persistent TABLE=1,INDEX=1;
INDEX


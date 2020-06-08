function testing

nIter = 10000;
max_size = 5000;

x = ones(max_size);

t1 = 0;

for i=1:nIter
    tic;
    x = f1(x);
    t1 = t1 + toc;
end
t1

xx = ones(max_size);

t2 = 0;

for i=1:nIter
    tic;
    xx=xx';
    t2 = t2 + toc;
end
t2

function y=f1(x)
y = x';

function f2()
xx=xx';
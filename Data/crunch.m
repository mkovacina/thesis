function [x, best, gens] = crunch(p)
% This is assuming that each generation in the data
% file is sorted, thus the first entry is the best

x = load(p.datafile);

best = x(1:p.n_soln:p.n_soln*p.n_gen,p.cols);

% this is done cols(:)' because the 'for' picks off by the column, not the element, thus this ensures that the input to i is a row vector
for i=1:numel(p.cols(:))
  c1 = x(:,p.cols(i));
  [r, c] = size(c1);
  n_Gen = r / p.n_soln;
  gens(:,:,i) = reshape(c1,p.n_soln,n_Gen);
end 

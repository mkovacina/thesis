function m = calcMean(p)

m = mean(p.gens);
m = reshape(m,p.n_gen,p.n_metrics); % because mean return size(m) = [1 3 3]
m = m ./ repmat( p.max, p.n_gen, 1 );

% m(:,p.revcols) = 1 - m(:,p.revcols); % convert to error measure
m(:,p.revcols) = m(:,p.revcols); 


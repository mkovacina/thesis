function s = calcStd(p)

s = std(p.gens);
s = reshape(s,p.n_gen,p.n_metrics); % because mean return size(m) = [1 3 3]
s = s ./ repmat( p.max, p.n_gen, 1 );

s(:,p.revcols) = 1 - s(:,p.revcols); % convert to error measure


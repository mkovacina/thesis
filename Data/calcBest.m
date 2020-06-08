function nbest = calcBest(p)

nbest=p.best./repmat( p.max, p.n_gen, 1);

% nbest(:,p.revcols) = 1-nbest(:,p.revcols);
nbest(:,p.revcols) = nbest(:,p.revcols);

nbest = [ nbest ];
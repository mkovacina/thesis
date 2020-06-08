function genPlot( p, data )

newplot;

title( p.title );
xlabel('Generation');
ylabel('Normalized Fitness');
axis( [ 1 p.n_gen 0 1.1 ] ) ;


hold on;

%set(gca,'LineStyleOrder', 'o|x|+|*|s|d|v|^');
%set(0,'DefaultAxesLineStyleOrder', 'o|x|+|*|s|d|v|^');
%set(gca,'LineStyleOrder',{'-*',':','o'});
lso = {'b:o','g:x','r:*','c:+','m:s'};
for x=1:p.n_metrics
    plot(data(:,x), lso{x});
end

%plot( data, 'd:' )

legend( deal(p.labels), 'Location','BestOutside' );
set(gcf,'Position',[0 0 768 512]);

hold off;


exportfig(gcf, p.pictfile, 'color', 'cmyk' );
%exportfig(gcf, p.pictfile);

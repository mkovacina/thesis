cdata = importdata('cloud_data.dat');

[ rows, cols ] = size(cdata)

hold on;

for i = 1:11:rows
    data = cdata( i:i+10, : );
    
    plot( data(:,4), data(:,5), '+b' );
    plot( data(i+10,4), data(i+10,5), 'or' );
end
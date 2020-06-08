function analyzedData = dataimporter()

file_struct = dir('data/data*');

[numFiles dummy] = size(file_struct);

% 1 - vehcile type
% 2 - swarm size
% 3 - sensor resolution
% 4 - cloud size
analyzedData = zeros(2,5,2,3,3);

for i=1:numFiles
    filename = strcat('data/',file_struct(i).name);
    fprintf('Importing [%2d] %s....\n',i,filename);
    
    indices = sscanf(filename,'data/data_T%d_%d_%2ds_%1c.dat');
    
    vehicleType = indices(1);
    swarmSize   = indices(2)/10;
    sensorRez   = indices(3)/10;
   
    switch(char(indices(4)))
        case 'S',
            cloudSize = 1;
        case 'M',
            cloudSize = 2;
        case 'L',
            cloudSize = 3;
    end
    
    data = importdata(filename);
    
    [a, b, c] = analyze(data);
    analyzedData(vehicleType, swarmSize, sensorRez, cloudSize, : ) = [a,b,c];
    
end

%%%% -----------------

function [numFound, avg, theMax ] = analyze(data)

numFound = data(end,1);

avgTimes = [ data(:,3) + (100-numFound)*50000 ];
avg = mean(avgTimes);

theMax = data(end,3);
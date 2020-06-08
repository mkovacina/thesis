function gengraphs(data)

mav_types = {'T1', 'T2'};
sensor_rez = { '10s', '20s' };

plot(s_size,array(:,1),'o-r',s_size,array(:,2),'x-b',s_size,array(:,3),'^-g')

for i=1:2
    for j=1:2
        fprintf('mav %s, sensor rez %s\n', mav_types{i}, sensor_rez{j});
        
        genSwarmSizeVsCloudSize(i,j,data);
        pause;
    end
end


%%%%% --------------------

function genSwarmSizeVsCloudSize(mavType,sensorType,data)

gdata = reshape( data( mavType, 1:5, sensorType, 1:3 ), 3, 5 );

waterfall(gdata);





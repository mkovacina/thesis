function [] = Cloud_map(t_delay,UAV_vel)

extent = 5000;   %   Extent of physical area being mapped (m)
N_grid = 201;    %   Number of grid points per 
 
 %  Build up arrays for 3-D plotting
 x_vec = linspace(0,extent,N_grid);
 y_vec = linspace(0,extent,N_grid);
 
 [X,Y] = meshgrid(x_vec,y_vec);
 
 %  Going to need to add some additional logic to flip the y-data
 %  We'll put it in later
 
 pheromone = zeros(N_grid,N_grid);    %  Mesh to store pheromone concentration
 delta = extent/(N_grid - 1);         %  Geographic increment for x and y
 
 
 sensor_rad = t_delay*UAV_vel;      %  Radius of sensor footprint
 ij_rad = round(sensor_rad/delta);  %  Indicial radius of sensor footprint
 
 %  Read in data and begin to set up everything
 N_UAVs = 10;
 cdata = importdata('cloud_data.dat');

 [t_max, cols] = size(cdata);
 t_max = floor(t_max/(N_UAVs + 1));      %  Number of time steps....
 
 %  Now we loop over every UAV at every time step and build up
 %  the pheromone map over time
 for i_time = 0:t_max
     for i_uav = 1:N_UAVs
         ii = i_time*(N_UAVs+1) + i_uav;
         
         %  Get coordinates of UAV and sensor state
         x = cdata(ii,4);
         y = cdata(ii,5);
         sense_flag = cdata(ii,3);
         
         %  Get location relative to pheromone map
         [i_loc,j_loc] = index_loc(x,y,delta);
         
         %  Adjust pheromone map accordingly....
         pheromone = updata_pheromone(i_loc,j_loc,sense_flag,N_grid,ij_rad,pheromone);
         
     end
     if mod(i_time,100) == 0
         %  Plot resulting pheromone gradient
         pher_max = max(realmin,max(max(pheromone)))
         pcolor(X,Y,pheromone/pher_max);
         shading interp;
         
         %  Update position of cloud
         ii = (i_time+1)*(N_UAVs+1);
         x_cloud = cdata(ii,4);
         y_cloud = cdata(ii,5);
         cloud_nodes = get_cloud(x_cloud,y_cloud);
         hold on
         plot(cloud_nodes(:,1),cloud_nodes(:,2),'-w')
         hold off
         
         i_time
         pause(.01)
     end
 end
         
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %  This will become another function....
 %  Computes the appropriate location on the pheromone map associated
 %  with each x-y pair
 function [i_x,j_y] = index_loc(x,y,delta)
 i_x = round(x/delta);
 j_y = round(y/delta);
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 %  This will become yet another function
 %  Computes the pheromone content within the indicial radius of the 
 %  sensor footprint associated with each UAV pair given the indicial
 %  location of the UAV, its sensor state, and the indicial sensor 
 %  radius.
 function A = updata_pheromone(i_x,j_y,sense_flag,N_grid,ij_rad,B)
 
 %   Define pheromone characteristics
 pher_inc = 10;                       %  Pheromone increment
 pher_dec = 1;                        %  Pheromone decrement
 pher_decay = 0.00002;                %  Pheromone decay rate
 
 %   Apply pheromone decay...
 B = B*(1. - pher_decay);
 
 %  Brute force but who cares....
 i_lo = max(1,i_x - ij_rad);            %  Get cartesian range of
 i_hi = min(i_x + ij_rad,N_grid);       %  sensor footprint and
 
 j_lo = max(1,j_y - ij_rad);            %  ...and check boundary           
 j_hi = min(j_y + ij_rad,N_grid);       %  of pheromone grid
 
 for ii = i_lo:i_hi
     x_bar(1) = ii-i_x;
     for jj = j_lo:j_hi
         x_bar(2) = jj - j_y;
         ij_dist = round(norm(x_bar));
         if ij_dist <= ij_rad   %  If inside the circle
             %  Check current pheromone level...
             Pher_flag = B(ii,jj) ~= 0;
             if sense_flag   %  Sensor is on...
                 B(ii,jj) = B(ii,jj) + pher_inc;
             elseif Pher_flag
                 B(ii,jj) = max(0,B(ii,jj) - pher_dec);
             end
         end
     end
 end
 
 A = B;
 
 %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 function poly_nodes = get_cloud(x_cloud,y_cloud)
 
 %   Compute the position of the points defining the nodes of the
 %   polygon being used to represent the chemical cloud.
 d = 300;                               %  Offset distance for nodes
 temp_vec = [x_cloud, y_cloud];
 poly_nodes = repmat(temp_vec,7,1);
 
 poly_nodes(1,:) = poly_nodes(1,:) + [d,0]; 
 poly_nodes(2,:) = poly_nodes(2,:) + [d,d];
 poly_nodes(3,:) = poly_nodes(3,:) + [0,d]; 
 poly_nodes(4,:) = poly_nodes(4,:) + [-d,0];            
 poly_nodes(5,:) = poly_nodes(5,:) + [-d,-d]; 
 poly_nodes(6,:) = poly_nodes(6,:) + [0,-d];    
 poly_nodes(7,:) = poly_nodes(1,:);
 
 
 
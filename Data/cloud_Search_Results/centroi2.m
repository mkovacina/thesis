function c = centroi2(x,y,A)
%  c = centroi2(x,y,A)
%  c = centroi2(A)
%  calculates the centroid or center of gravity of matrix A
%  c contains [x y] or [(column "index") (row "index")] coordinates

%	Author(s): R. Johnson
%	$Revision: 1.0 $  $Date: 1995/11/28 $

if nargin ==1
	A = x;
	[Ar Ac] = size(A);
	x = 1:Ac;
	y = (1:Ar)';
	else
	y = y(:);
	x = (x(:))';
	end

sumA = sum(A(:));
[rows cols] = size(A);
%	find centroid across columns
temp = A.*(ones(rows,1)*x);
c(1) = sum(temp(:))/(sumA);
%	find centroid across rows
temp = A.*(y*ones(1,cols));
c(2) = sum(temp(:))/(sumA);
  
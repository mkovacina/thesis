function [minC,Opt_Decision]=assignment_dpta(cost)

sc=size(cost);

n_agent=sc(1);
n_task=sc(2);

Fcost=0;
Fdec=0;
Fstate=0;

for i=1:n_agent
   Fcost(i)=cost(i,1);
   Fdec(i,1)=i;
   Fstate(i,i)=1;
end

for n=2:n_task
   [Fcost1,Fdec1,Fstate1]=bcast(cost,Fcost,Fdec,Fstate,n_agent,n);
   
   clear Fcost;
   clear Fstate;
   clear Fdec;
   
   Fcost=Fcost1;
   Fdec=Fdec1;
   Fstate=Fstate1;
   
   clear Fcost1;
   clear Fstate1;
   clear Fdec1;
end

sFc=size(Fcost);

minC=Fcost(1);
minI=1;

for i=2:sFc
   if Fcost(i)<minC
      minC=Fcost(i);
      minI=i;
   end
end

Opt_Decision=Fdec(minI,:);


%% ---------
function [Fcost1,Fdec1,Fstate1]=bcast(cost,Fcost,Fdec,Fstate,n_agent,n)

sFs=size(Fstate);

for i=1:n_agent
   k=0;
   for j=1:sFs(1)
      if Fstate(j,i)==0
         k=k+1;
         tdec(k,:,i)=[Fdec(j,:),0];
         tdec(k,n,i)=i;
         if dconst(tdec(k,:,i))==1
            tcost(k,i)=Fcost(j)+cost(i,n);
            tstate(k,:,i)=Fstate(j,:);
            tstate(k,i,i)=1;
         else
            tcost(k,i)=-1;
            tstate(k,:,i)=Fstate(j,:);
            tstate(k,i,i)=1;
         end
      end
   end
end

stc=size(tcost);

for i=1:n_agent
   for j=1:stc(1)
      if tcost(j,i)~=-1
         bcost=tcost(j,i);
         bstate=tstate(j,:,i);
         for ii=1:n_agent
            if ii~=i
               for jj=1:stc(1)
                  if tcost(jj,ii)~=-1 & tstate(jj,:,ii)==bstate
                     if tcost(jj,ii)>=bcost
                        tcost(jj,ii)=-1;
                     end
                  end
               end
            end
         end
      end
   end
end

k=0;
for i=1:n_agent
   for j=1:stc(1)
      if tcost(j,i)~=-1
         k=k+1;
         Fcost1(k)=tcost(j,i);
         Fdec1(k,:)=tdec(j,:,i);
         Fstate1(k,:)=tstate(j,:,i);
      end
   end
end

%% ---------
function y=dconst(dec)

if dec(2)==1
   if dec(1)==2
      y=1;
   else
      y=0;
   end
else
   y=1;
end



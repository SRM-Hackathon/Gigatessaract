[A,B,C,D,E,F] = textread ('dictionary.tsv','%s %d %s %s %s %s','headerlines',1);
[id, label] = xlsread('sentiment2.xlsx');
idtodel = zeros;
for i = 1:size(id,1)
    if id(i)==0
        idtodel(i) = 1;
    end
end
idtodel = (id==0);

%X(idtodel, :) = [];
%id(idtodel, :) = [];
for i = 1:size(id,1) 
    if(id(i)==-1) id(i) = 0;
    end;
end;
label = char(label(1:3575));
%G = cell2mat(label);
fid = fopen('tweetdata.txt');
str = importdata('tweetdata.txt');
%str = textscan(fid, '%c', 'Delimiter', '\n\n');
idsrt = [""];
len = 3574;
Theta = zeros;
for i = 1: len+1
    Theta(i)  = rand();
end;
X = zeros(len,8221);
temp = repmat('h', 3574,80);
for i  =1:len
  temp(i, :) = str{i}(118:197);
  X(1, :) = featureLabel(temp(i,:),C);

  
  
end

X(idtodel, :) = [];
id(idtodel, :) = [];

model = fitcsvm(X, id);
[label,score] = predict(model,X(2000,:));
disp(score);


%idsrt(i) = temp(53:70);

%disp(idstr(5));
%for(i=1:size(idx,1))
%    idy(i) = str(idy(i):idy(i+10));
%end
    

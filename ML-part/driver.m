[A,B,C,D,E,F] = textread ('dictionary.tsv','%s %d %s %s %s %s','headerlines',1);
[id, label] = xlsread('sentiment2.xlsx');
idtodel = zeros;




idtodel = (id(:,1)==0);
%X(id(:,1)==0, :) = [];
%id(id(:,1)==0, :) = [];
s = size(id,1);
X = zeros(s, size(C,1));
idtoshrink = (id(:,1)==-1);
label = char(label(1:3575));
%G = cell2mat(label);
fid = fopen('tweetdata.txt');
str = importdata('tweetdata.txt');
%str = textscan(fid, '%c', 'Delimiter', '\n\n');
temp = repmat('h', 3574,140);
for i=1:s
  temp(i, :) = str{i}(118:257);
  X(i, :) = featureLabel(temp(i,:),C);
end

X(idtodel, :) = [];
id(idtodel, :) = [];
for i = 1:size(idtoshrink,1)
    id(idtoshrink(i),1)  = 0;
end
index = floor(0.6*size(X,1));
model = fitcsvm(X, id);
%maxscore = 0;
%disp(score);

%disp(maxscore);
%idsrt(i) = temp(53:70);

%disp(idstr(5));
%for(i=1:size(idx,1))
%    idy(i) = str(idy(i):idy(i+10));
%end
    

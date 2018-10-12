function [x, label] = featureLabel(contents, id)
  [A,B,C,D,E,F] = tdfread("dictionary.tsv", '%s %d %s %s %s %s');
  
contents = read_file('message.txt');
len = size(C);
x = zeros(len,1);
feature = [];

  while ~isempty(contents)
  
    [str, contents] = ...
     strtok(contents, ' @$/#.-:&*+=[]?!(){},''">_<;%');
     
     
    try str = porterStemmer(strtrim(str)); 
    catch str = ''; continue;
    end;

  
    if length(str) < 1
       continue;
    end
       for(i=1:len)
    if(strcmp(C(i), str)==1)
    feature = [feature;i];  
    end
  end
  for(i=1:size(feature,1))
  x(feature(i)) = 1;
end
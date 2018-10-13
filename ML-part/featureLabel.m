function [x] = featureLabel(contents,C)
len = size(C);
x = zeros;
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
end
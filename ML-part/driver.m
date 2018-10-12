[A,B,C,D,E,F] = textread ('dictionary.tsv','%s %d %s %s %s %s','headerlines',1);
  [id, label] = xlsread('sentiment2.xls');
  fid = fopen('tweetdata.txt');
  s = textscan(fid);
  idx = strfind(str, pattern);
  id = zeros(size(id,1));
  for(i=1:size(idx,1))
  id(i) = str(id(i):id(i+10));
  endfor
  

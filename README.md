

For learning git and github.

local:

git init

git add .

git commit -m "..."

git branch -a(查看所有分支包括本地分支和远程分支).

git branch -r(查看远程分支).

git checkout -b branchname(直接新建一个分支然后切换至新创建的分支).就是创建加切换分支.

等价于命令：git branch branchname+git checkout branchname.

git checkout branchname(是切换分支名).

associate:

git remote add origin git@github.com:.../....git

update:

//git pull origin master (--allow-unrelated-histories)

git push -uf origin master

git push origin master

git push origin branch_name

2020.7.6

# hadoop命令
	#查看
	hadoop fs -ls /
	#查看文件
	hadoop fs -cat /storm-hdfs
	#删除文件夹
	hadoop fs -rm -r -skipTrash /storm-hdfs
	
	#集群运行
	storm jar stormhdfs.jar cn.fy.DataToHdfsApp cluster
	#查看管理页面
	http://172.16.1.82:8081/

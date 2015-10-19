date=$(date |sed 's/ /_/g')
cd $1
cd ..
mv "PHP" "PHP_backup"$date


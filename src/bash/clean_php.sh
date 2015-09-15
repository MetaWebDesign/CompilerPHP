date=$(date |sed 's/ /_/g')
#tar -czvf $1/php_backup_$date.tar.gz $1"/PHP/"
mv -fv $1"/PHP/" $1"/PHP_backup"$date

elect distinct sigfox_id, sensor_id, max(date) from meteo_log  where date > CURRENT_DATE - INTERVAL '1 month' group by sigfox_id, sensor_id order by sensor_id;


<?php
$fn = fopen("IPs.txt", "r");
$ips = array();
while (!feof($fn)) {
    $result = fgets($fn);
    $current = new IpAddress($result);
    $ips[$current->toInt()] = $current;
}
fclose($fn);

class IpAddress
{
    public $parts;
    function __construct($string_IP)
    {
        if (is_string($string_IP)) {
            $string_IP = trim($string_IP);
            $string_parts = explode('.', $string_IP);
            if (count($string_parts) > 4) {
                $this->parts = array();
            } else {
                for ($i = 0; $i < 4; $i++) {
                    if ($i < count($string_parts)) {
                        $int_value = ctype_digit($string_parts[$i]) ? intval($string_parts[$i]) : null;
                        if ($int_value != null) {
                            if ($int_value > 255) {
                                $this->parts[$i] = 255;
                            } elseif ($int_value < 0) {
                                $this->parts[$i] = 0;
                            } else {
                                $this->parts[$i] = $int_value;
                            }
                        } else {
                            $this->parts[$i] = 0;
                        }
                    } else {
                        $this->parts[$i] = 0;
                    }
                }
            }
        } else {
            $this->parts = array(0, 0, 0, 0);
        }
    }

    public function __toString()
    {
        return implode(".", $this->parts);
    }

    public function toInt()
    {
        $int_value = 0;
        for ($i = 3; $i >= 0; $i--) {
            $int_value += pow(255, $i) * $this->parts[$i];
        }
        return $int_value;
    }
}

$connection =  new mysqli("server", "user", "pass", "database");
foreach ($ips as $ip) {
    $sql = "INSERT INTO IpTable VALUES (" . $ip . ")";
    if ($conn->query($sql) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
}
$connection->close();
// $fp = fopen('unique-IPs.txt', 'w');
// foreach ($ips as $ip) {
    
//     fwrite($fp, $ip->__toString() . "\r\n");
// }
// fclose($fp);

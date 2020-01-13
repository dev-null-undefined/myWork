#include "mbed.h"
#include "XNucleoIKS01A2.h"
static XNucleoIKS01A2 *mems_expansion_board = XNucleoIKS01A2::instance(D14, D15, D4, D5);
static LSM303AGRMagSensor *magnetometer = mems_expansion_board->magnetometer;
static HTS221Sensor *hum_temp = mems_expansion_board->ht_sensor;
static LPS22HBSensor *press_temp = mems_expansion_board->pt_sensor;
static LSM6DSLSensor *acc_gyro = mems_expansion_board->acc_gyro;
static LSM303AGRAccSensor *accelerometer = mems_expansion_board->accelerometer;
static char *print_double(char* str, double v, int decimalDigits=3)
{
  int i = 1;
  int intPart, fractPart;
  int len;
  char *ptr;
  for (;decimalDigits!=0; i*=10, decimalDigits--);
  intPart = (int)v;
  fractPart = (int)((v-(double)(int)v)*i);
  sprintf(str, "%i.", intPart);
  len = strlen(str);
  ptr = &str[len];
  for (i/=10;i>1; i/=10, ptr++) {
    if (fractPart >= i) {
      break;
    }
    *ptr = '0';
  }
  sprintf(ptr, "%i", fractPart);
  return str;
}
int main() {
  float value1, value2;
  float value3, value4;
  char buffer1[32], buffer2[32];
  char buffer3[32], buffer4[32];
  int32_t axes1[3];
  int32_t axes2[3];
  int32_t axes3[3];
  int32_t axes4[3];
  hum_temp->enable();
  press_temp->enable();
  magnetometer->enable();
  accelerometer->enable();
  acc_gyro->enable_x();
  acc_gyro->enable_g();
  while(1) {    
    hum_temp->get_temperature(&value1);
    hum_temp->get_humidity(&value2);
    press_temp->get_temperature(&value3);
    press_temp->get_pressure(&value4);
    magnetometer->get_m_axes(axes1);
    accelerometer->get_x_axes(axes2);
    acc_gyro->get_x_axes(axes3);
    acc_gyro->get_g_axes(axes4);
    printf("INSERT INTO Report(Senzor_id,Date_time,HTS221_temp,HTS221_hum,LPS22HB_temp,LPS22HB_press,LSM303AGR_mag_x,LSM303AGR_mag_y,LSM303AGR_mag_z,LSM303AGR_acc_x,LSM303AGR_acc_y,LSM303AGR_acc_z,LSM6DSL_gyro_x,LSM6DSL_gyro_y,LSM6DSL_gyro_z,LSM6DSL_acc_x,LSM6DSL_acc_y,LSM6DSL_acc_z)VALUES('1',NOW(),%s,%s,%s,%s,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld,%ld);\n\r",print_double(buffer1, value1), print_double(buffer2, value2),print_double(buffer3, value3), print_double(buffer4, value4),axes1[0], axes1[1], axes1[2],axes2[0], axes2[1], axes2[2],axes3[0], axes3[1], axes3[2],axes4[0], axes4[1], axes4[2]);
    wait(2);
  }
}
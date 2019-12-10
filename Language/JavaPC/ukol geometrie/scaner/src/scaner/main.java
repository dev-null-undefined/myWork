package scaner;

import java.util.Scanner;

public class main {
	public static boolean isInteger(String s) {// copy from web
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;

	}

	public static boolean isDouble(String b) {
		try {
			double a = Double.valueOf(b);
		} catch (NumberFormatException e) {
			return false;
		} catch (NullPointerException e) {
			return false;
		}
		return true;

	}

	public static boolean isMustDouble(Double a) {
		if (a % 1 == 0) {
			return true;
		}
		return false;
	}

	public static int jakecislo(int e) {
		int fals = 2;
		boolean must;
		int tru = 1;
		int tr = 3;
		if (e == 1) {
			String a, c;
			boolean b, d;
			double aa, bb;
			System.out.print("zadejte a:");
			Scanner in = new Scanner(System.in);
			a = in.nextLine();
			b = isDouble(a);
			if (b == true) {
				System.out.print("zadejte b:");
				c = in.nextLine();
				d = isDouble(c);
				if (d == true) {
					aa = Double.valueOf(a);
					bb = Double.valueOf(c);
					obdelnik Obdelnik = new obdelnik(aa, bb);
					must = isMustDouble(Obdelnik.getObsah());
					if (must == true) {
						System.out.format("Obsah obdelniku je: %.0f", Obdelnik.getObsah());
						System.out.println((char) 178);
						return fals;
					} else {
						System.out.format("Obsah obdelniku je: %.2f", Obdelnik.getObsah());
						System.out.println((char) 178);
						return fals;
					}
				} else {
					errordva(c);
					return tr;

				}
			}

			else {
				errordva(a);
				return tr;

			}

		} else {
			if (e == 2) {
				String a;
				boolean b;
				Double aa;
				System.out.print("zadejte r:");
				Scanner in = new Scanner(System.in);
				a = in.nextLine();
				b = isDouble(a);
				if (b == true) {
					Double e1 = Double.valueOf(a);
					if (e1 > 0) {
						aa = Double.valueOf(a);
						kruh Kruh = new kruh(aa);
						must = isMustDouble(Kruh.getObsah());
						boolean must2;
						must2 = isMustDouble(Kruh.getObvod());
						if (must2 == true) {
							System.out.format("Obsah kruhu je: %.0f%n", Kruh.getObvod());
						} else {
							System.out.format("Obsah kruhu je: %.2f%n", Kruh.getObvod());
						}
						if (must == true) {
							System.out.format("Obsah kruhu je: %.0f", Kruh.getObsah());
							System.out.println((char) 178);
							return fals;
						} else {
							System.out.format("Obsah kruhu je: %.2f", Kruh.getObsah());
							System.out.println((char) 178);
							return fals;
						}
					} else {
						errordva(a);
						return tr;

					}
				} else {
					errordva(a);
					return tr;

				}

			} else {
				if (e == 3) {
					String a, c;
					boolean b, d;
					double aa, bb;
					System.out.print("zadejte a:");
					Scanner in = new Scanner(System.in);
					a = in.nextLine();
					b = isDouble(a);
					if (b == true) {
						System.out.print("zadejte Va:");
						c = in.nextLine();
						d = isDouble(c);
						if (d == true) {
							aa = Double.valueOf(a);
							bb = Double.valueOf(c);
							trojuhelnik troj = new trojuhelnik(aa, bb);
							must = isMustDouble(troj.getObsah());
							if (must == true) {
								System.out.format("Obsah trojuhelnik je: %.0f", troj.getObsah());
								System.out.println((char) 178);
								return fals;
							} else {
								System.out.format("Obsah trojuhelnik je: %0.2f", troj.getObsah());
								System.out.println((char) 178);
								return fals;
							}
						} else {
							errordva(c);
							return tr;

						}
					}

					else {
						errordva(a);
						return tr;

					}

				} else {
					if (e == 4) {
						String a;
						boolean b;
						Double aa;
						System.out.print("zadejte a:");
						Scanner in = new Scanner(System.in);
						a = in.nextLine();
						b = isDouble(a);
						aa = Double.valueOf(a);
						if (b == true) {
							ctverec Ctverec = new ctverec(aa);
							must = isMustDouble(aa);
							if (must == true) {
								System.out.format("obsah: %.0f", Ctverec.getObsah());
								System.out.println((char) 178);
								System.out.format("obvod: %.0f%n", Ctverec.getObvod());
								return fals;
							} else {
								System.out.format("obsah: %.2f", Ctverec.getObsah());
								System.out.println((char) 178);
								System.out.format("obvod: %.2f%n", Ctverec.getObvod());
								return fals;

							}
						}

						else {
							error(a);
							return tru;

						}

					} else {
						if (e == 5) {
							String a;
							boolean b;
							double aa;
							System.out.print("zadejte a:");
							Scanner in = new Scanner(System.in);
							a = in.nextLine();
							b = isDouble(a);
							aa = Double.valueOf(a);
							if (b == true) {
								krychle Krych = new krychle(aa);
								System.out.println("objem:" + Krych.getObjem() + (char) 178);
								System.out.println("povrch:" + Krych.getPovrch() + (char) 179);
								return fals;
							}

							else {
								errordva(a);
								return tr;

							}

						} else {
							String a;
							boolean b;
							Double aa;
							System.out.print("zadejte r:");
							Scanner in = new Scanner(System.in);
							a = in.nextLine();
							b = isDouble(a);
							if (b == true) {
								Double e1 = Double.valueOf(a);
								if (e1 > 0) {
									aa = Double.valueOf(a);
									koule Koule = new koule(aa);
									must=isMustDouble(Koule.getObjem());
									if (must==true) {
										System.out.format("Objem koule je: %.0f" , Koule.getObjem());
										System.out.println((char)178);
									}else {
										System.out.format("Objem koule je: %.2f" , Koule.getObjem());
										System.out.println((char)178);
									}
									boolean must2=isMustDouble(Koule.getPovrch());
									if(must2) {
										System.out.format("Povrch koule je: %.0f", Koule.getPovrch());
										System.out.println((char)178);
										return fals;
									}else {
										System.out.format("Povch koule je: %.2f", Koule.getPovrch());
										System.out.println((char)178);
										return fals;
									}
										
								} else {

									errordva(a);
									return tr;

								}
							} else {

								errordva(a);
								return tr;

							}

						}
					}
				}
			}

		}
	}

	public static void error(String a) {
		System.out.println("YOUR INPUT IS NOT INTAGER PLEASE DO NOT MAKE ME ANGRY!!!!");
		System.out.println("(This is your input== " + a + ")");
		System.out.println("You can try again.");
	}

	public static void errordva(String a) {
		System.out.println("YOUR INPUT IS NOT DOUBLE must be like n.x ok?!");
		System.out.println("(This is your input== " + a + ")");
		System.out.println("You can try again.");
	}

	public static void main(String[] args) {
		String a = null, f;
		int e, d = 4;
		boolean b;
		int run = 1;
		while (run == 1 || run == 3) {
			if (run == 1) {
				boolean c = true;
				System.out.println("Co chceš??");
				System.out.println("1)obdelnik");
				System.out.println("2)kruh");
				System.out.println("3)trojuhelnik");
				System.out.println("4)ctverec");
				System.out.println("5)krychle");
				System.out.println("6)koule");
				Scanner in = new Scanner(System.in);
				a = in.nextLine();
				b = isInteger(a);
				if (b == true) {
					e = Integer.valueOf(a);
					if (e >= 1 && e <= 6) {
						run = jakecislo(e);
					} else {
						System.out.println("Write number from 1-6.");
						System.out.println("Your input is=" + e);
					}
				} else {
					error(a);
				}
			} else {
				while (run == 3) {
					e = Integer.valueOf(a);
					run = jakecislo(e);

				}
			}
		}

	}
}

package Util;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MergerMp3 {
	private Context context;
	public static final String FILENAME = "temp";
	private int[] lotterys;
	MediaPlayer play;

	public MergerMp3(Context context) {
		this.context = context;
	}

	public void setLotterys(int[] lotterys) {
		this.lotterys = lotterys;
		Log.d("hehe", this.lotterys[0] + " " + this.lotterys[1] + " "
				+ this.lotterys[2] + " " + this.lotterys[3] + " "
				+ this.lotterys[4] + " " + this.lotterys[5]);
	}

	// �ϲ�2�������ļ�
	private File merger2(String f1, String f2) {
		File newFile = null;
		try {
			newFile = File.createTempFile(FILENAME, ".mp3",
					context.getCacheDir());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		try {
			int length;
			InputStream inputStream1 = context.getAssets().open(f1 + ".mp3");
			InputStream inputStream2 = context.getAssets().open(f2 + ".mp3");
			FileOutputStream fileWriter = new FileOutputStream(newFile);
			synchronized (MergerMp3.class) {
				int j = 0;
				while ((length = inputStream1.read(buffer)) > 0) {
					if (j <= 5) {
						fileWriter.write(buffer, 0, length);
						j++;
					} else {
						break;
					}
				}

				inputStream1.close();
			}
			synchronized (MergerMp3.class) {
				while ((length = inputStream2.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream2.close();
				fileWriter.close();
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}

	// �ϲ�3�������ļ�
	public File merger3(String f1, String f2, String f3) {
		File newFile = null;
		try {
			newFile = File.createTempFile(FILENAME, ".mp3",
					context.getCacheDir());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		try {
			int length;
			InputStream inputStream1 = context.getAssets().open(f1 + ".mp3");
			InputStream inputStream2 = context.getAssets().open(f2 + ".mp3");
			InputStream inputStream3 = context.getAssets().open(f3 + ".mp3");
			FileOutputStream fileWriter = new FileOutputStream(newFile);
			synchronized (MergerMp3.class) {
				int j = 0;
				while ((length = inputStream1.read(buffer)) > 0) {
					if (j <= 6) {
						fileWriter.write(buffer, 0, length);
						j++;
					} else {
						break;
					}
				}

				inputStream1.close();
			}
			synchronized (MergerMp3.class) {
				int i = 0;
				while ((length = inputStream2.read(buffer)) > 0) {
					if (i <= 5) {
						fileWriter.write(buffer, 0, length);
						i++;
					} else {
						break;
					}
				}
				inputStream2.close();
			}

			synchronized (MergerMp3.class) {
				int i = 0;
				while ((length = inputStream3.read(buffer)) > 0) {
						fileWriter.write(buffer, 0, length);
				}
				inputStream3.close();
				fileWriter.close();
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}

	// �ϲ��������ļ�
	private File merger4(String f1, String f2, String f3, String f4) {
		// File file1;
		// File file2;
		File newFile = null;
		try {
			newFile = File.createTempFile(FILENAME, ".mp3",
					context.getCacheDir());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		try {
			int length;
			InputStream inputStream1 = context.getAssets().open(f1 + ".mp3");
			InputStream inputStream2 = context.getAssets().open(f2 + ".mp3");
			InputStream inputStream3 = context.getAssets().open(f3 + ".mp3");
			InputStream inputStream4 = context.getAssets().open(f4 + ".mp3");
			FileOutputStream fileWriter = new FileOutputStream(newFile);
			synchronized (MergerMp3.class) {
				while ((length = inputStream1.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream1.close();
			}
			synchronized (MergerMp3.class) {
				while ((length = inputStream2.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream2.close();
			}

			synchronized (MergerMp3.class) {
				while ((length = inputStream3.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream3.close();
			}

			synchronized (MergerMp3.class) {
				while ((length = inputStream4.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream4.close();
				fileWriter.flush();
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}
	
	
	private File merger8(File f1, InputStream f2, File f3, File f4) {
		// File file1;
		// File file2;
		File newFile = null;
		try {
			newFile = File.createTempFile(FILENAME, ".mp3",
					context.getCacheDir());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		try {
			int length;
			InputStream inputStream1 = context.getAssets().open("benqikaijiang.mp3");
			InputStream inputStream2 = new FileInputStream(f1);
			
			InputStream inputStream3 = context.getAssets().open("type.mp3");
			InputStream inputStream4 = f2;
			
			InputStream inputStream5 = context.getAssets().open("ermaguanzhu.mp3");
			InputStream inputStream6 = new FileInputStream(f3);
			
			InputStream inputStream7 = context.getAssets().open("sanmaguanzhu.mp3");
			InputStream inputStream8 = new FileInputStream(f4);
			
			InputStream inputStream9 = context.getAssets().open("bay.mp3");
			FileOutputStream fileWriter = new FileOutputStream(newFile);
			synchronized (MergerMp3.class) {
				int i1=0;
				while ((length = inputStream1.read(buffer)) > 0) {
					if(i1 <2){
						fileWriter.write(buffer, 0, length);
						//i1++;
					}
					
				}
				inputStream1.close();
			}
			synchronized (MergerMp3.class) {
				while ((length = inputStream2.read(buffer)) > 0) {
					
						fileWriter.write(buffer, 0, length);
					
				}
				inputStream2.close();
			}
			synchronized (MergerMp3.class) {
				int i3=0;
				while ((length = inputStream3.read(buffer)) > 0) {
					if(i3<17){
						i3++;
						fileWriter.write(buffer, 0, length);
					}
					
				}
				inputStream3.close();
			}synchronized (MergerMp3.class) {
				while ((length = inputStream4.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream4.close();
			}

			synchronized (MergerMp3.class) {
				int i5=0;
				while ((length = inputStream5.read(buffer)) > 0) {
					if(i5<70){
						i5++;
						fileWriter.write(buffer, 0, length);
					}
					
				}
				inputStream5.close();
			}
			synchronized (MergerMp3.class) {
				while ((length = inputStream6.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream6.close();
			}synchronized (MergerMp3.class) {
				int i7=0;
				while ((length = inputStream7.read(buffer)) > 0) {
					if(i7<40){
						i7++;
						fileWriter.write(buffer, 0, length);
					}
					
				}
				inputStream7.close();
			}synchronized (MergerMp3.class) {
				while ((length = inputStream8.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream8.close();
			}

			synchronized (MergerMp3.class) {
				while ((length = inputStream9.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream9.close();
				fileWriter.flush();
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}
	
	
	
	

	// �ϲ�ȫ����2,��3�ļ�
	private File sum23(File f1, File f2, File f3) {
		File newFile = null;
		try {
			newFile = File.createTempFile(FILENAME, ".mp3",
					context.getCacheDir());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		byte[] buffer = new byte[1024];
		try {
			int length;
			InputStream inputStream1 = new FileInputStream(f1);
			InputStream inputStream2 = new FileInputStream(f2);
			InputStream inputStream3 = new FileInputStream(f3);
			FileOutputStream fileWriter = new FileOutputStream(newFile);
			synchronized (MergerMp3.class) {
				while ((length = inputStream1.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream1.close();
			}
			synchronized (MergerMp3.class) {
				while ((length = inputStream2.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream2.close();
			}

			synchronized (MergerMp3.class) {
				while ((length = inputStream3.read(buffer)) > 0) {
					fileWriter.write(buffer, 0, length);
				}
				inputStream3.close();
				fileWriter.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newFile;
	}

	// ��ɵ�����2�ļ�
	private File z2(int number) {
		int number2 = number % 10;
		number /= 10;
		int number1 = number;
		return merger2("p" + number1, "p" + number2);
	}

	// ��ɵ�����3�ļ�
	private File z3(int number) {
		int number3 = number % 10;
		number /= 10;
		int number2 = number % 10;
		number /= 10;
		int number1 = number;
		return merger3("p" + number1, "p" + number2, "p" + number3);
	}

	public void play() {
		File fkn = merger4("p" + lotterys[0], "p" + lotterys[1], "p"
				+ lotterys[2], "p" + lotterys[3]);
		InputStream in = null;
		try{
			if(lotterys[4] == 4){
				in = context.getAssets().open("z4.mp3");
			}else if(lotterys[4] == 6){
				in = context.getAssets().open("z6.mp3");
			}else if(lotterys[4] == 12){
				in = context.getAssets().open("z12.mp3");
			}else if(lotterys[4] == 24){
				in = context.getAssets().open("z24.mp3");
			}
			
			File fz2 = sum23(z2(lotterys[5]), z2(lotterys[6]),
					z2(lotterys[7]));
			
			File fz3 = sum23(z3(lotterys[8]), z3(lotterys[9]),
					z3(lotterys[10]));
			File ff = merger8(fkn,in,fz2,fz3);
			play = new MediaPlayer();
			play.setDataSource(ff.getPath());
			play.prepare();
			play.start();
		}catch(Exception e){
			
		}
	}
	public void stop(){
		if(play!=null)
		play.stop();
	}

}

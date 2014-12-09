package com.example.aad2project.server;

import java.net.MalformedURLException;

import android.content.Context;
import android.util.Log;

import com.example.aad2project.object.ExistingPlant;
import com.example.aad2project.object.Weather;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.TableOperationCallback;

public class CloudInitialize {

	/**
	 * Mobile Service Client reference
	 */
	private MobileServiceClient mClient;

	/**
	 * Mobile Service Table used to access data
	 */
	private MobileServiceTable<ExistingPlant> tableExistingPlant;
	private MobileServiceTable<Weather> tableWeather;
	
	/**
	 * Inserts all the Exisiting Plant and the weathers to the server
	 */
	public CloudInitialize(Context context) {
		try {
			mClient = new MobileServiceClient(
					"https://greenhub.azure-mobile.net/",
					"TnxUiYgqqbPovNDQHROYaqrALQQUMw32", context);

			// Get the Mobile Service Table instance to use
			tableExistingPlant = mClient.getTable(ExistingPlant.class);
			tableWeather = mClient.getTable(Weather.class);

			if (mClient == null) {
				Log.d("TAG", "mCLientNull");

				return;
			}

			// Create a new item
			ExistingPlant p = new ExistingPlant();

			p.setPlantId(6);
			p.setName("Lillies");
			p.setDescription("Lilies are a group of flowering plants which are important in culture and literature in much of the world. Lily flowers are valued for their very showy, often fragrant flowers. You should plant the bulbs in autumn and they need from 6 to 8 hours of sunlight. Regarding the soil, lilies require a well-drained site as water trapped beneath the scales may rot the bulb. In active growth, water freely and apply a high-potash liquid fertilizer every 2 weeks.");
			p.setTimeToGrow(30);
			p.setWeatherId(6);
			// Insert the new item
			tableExistingPlant.insert(p,
					new TableOperationCallback<ExistingPlant>() {

						public void onCompleted(ExistingPlant entity,
								Exception exception,
								ServiceFilterResponse response) {

							if (exception == null) {
								Log.d("TAG", "inserted");
							} else {
								Log.d("EXCEPTION", exception.toString());
								Log.d("TAG",
										" getCause = " + exception.getCause());
								Log.d("TAG",
										" getStackTrace = "
												+ exception.getStackTrace());
							}

						}
					});

			p.setPlantId(5);
			p.setName("Sweet Peas");
			p.setDescription("Sweet peas enchant us with their fragile, seductive fragrance and make great bouquets. These pea-like flowers grow in many lovely colours and are suitable for an annual border, a woodland garden, and a trellis or arch. Early sowing is one of the secrets of sweet peas. The bloom time for this flower is in summer and fall. Before planting, soak the seeds in water for 1 day. If you are in temperate climate you do not need to do so. Germination can take 7 to 15 days and you must keep the soil humid and moist. Sweet peas prefer cool days and nights and will start to fade when temperatures go above 65 Fº. They are climbers so give them at least 1.8 m of good support.");
			p.setTimeToGrow(70);
			p.setWeatherId(5);
			// Insert the new item
			tableExistingPlant.insert(p,
					new TableOperationCallback<ExistingPlant>() {

						public void onCompleted(ExistingPlant entity,
								Exception exception,
								ServiceFilterResponse response) {

							if (exception == null) {
								Log.d("TAG", "inserted");
							} else {
								Log.d("EXCEPTION", exception.toString());
								Log.d("TAG",
										" getCause = " + exception.getCause());
								Log.d("TAG",
										" getStackTrace = "
												+ exception.getStackTrace());
							}

						}
					});

			p.setPlantId(4);
			p.setName("Lettuces");
			p.setDescription("Lettuce is a green annual plant which is most often grown as a leaf vegetable. It is easily cultivated, although it requires relatively low temperatures to prevent it from flowering quickly. It can be planted almost throughout all the year and it takes between 20 and 65 days to grow. The soil must be very rich in nutrients and have to drain water very good. You should also maintain the ground humid. You can harvest you lettuce during all the flourishing period, as they will always be very tasty.");
			p.setTimeToGrow(56);
			p.setWeatherId(4);
			// Insert the new item
			tableExistingPlant.insert(p,
					new TableOperationCallback<ExistingPlant>() {

						public void onCompleted(ExistingPlant entity,
								Exception exception,
								ServiceFilterResponse response) {

							if (exception == null) {
								Log.d("TAG", "inserted");
							} else {
								Log.d("EXCEPTION", exception.toString());
								Log.d("TAG",
										" getCause = " + exception.getCause());
								Log.d("TAG",
										" getStackTrace = "
												+ exception.getStackTrace());
							}

						}
					});

			p.setPlantId(2);
			p.setName("Carrots");
			p.setDescription("Carrots are a root vegetable, usually orange and with a crisp texture when fresh. They provide a good source of A vitamin which is good for the sight. They take between 2 and 3 months to grow and they should be planted either in spring, autumn or winter. Carrot seeds need a lot of humidity so two days before planting you could put the seeds between two humid papers. Make a hole of 1 cm deep in the ground and pour more than one seed because some will not germinate. They like cold climate but sunny at the same time. It is recommended that they get sun throughout all the day. When watering carrots you have to make sure that the water reaches deep in the hole.");
			p.setTimeToGrow(120);
			p.setWeatherId(2);
			// Insert the new item
			tableExistingPlant.insert(p,
					new TableOperationCallback<ExistingPlant>() {

						public void onCompleted(ExistingPlant entity,
								Exception exception,
								ServiceFilterResponse response) {

							if (exception == null) {
								Log.d("TAG", "inserted");
							} else {
								Log.d("EXCEPTION", exception.toString());
								Log.d("TAG",
										" getCause = " + exception.getCause());
								Log.d("TAG",
										" getStackTrace = "
												+ exception.getStackTrace());
							}

						}
					});

			p.setPlantId(1);
			p.setName("Potatoes");
			p.setDescription("Potatoes are a key for anybody who seeks to be self-sufficient. The potato is a starchy, tuberous crop that came from America but grow easily all over the world. It takes between 65 and 100 days to grow, and it should planted in spring. The soil should always be humid so you should water the around 3 times a week. When they flourish they will need more water. They can be harvested whenever they flourish and they should leave on the ground 1 day so they get dry.");
			p.setTimeToGrow(10);
			p.setWeatherId(1);
			// Insert the new item
			tableExistingPlant.insert(p,
					new TableOperationCallback<ExistingPlant>() {

						public void onCompleted(ExistingPlant entity,
								Exception exception,
								ServiceFilterResponse response) {

							if (exception == null) {
								Log.d("TAG", "inserted");
							} else {
								Log.d("EXCEPTION", exception.toString());
								Log.d("TAG",
										" getCause = " + exception.getCause());
								Log.d("TAG",
										" getStackTrace = "
												+ exception.getStackTrace());
							}

						}
					});

			p.setPlantId(3);
			p.setName("tomatoes");
			p.setDescription("Tomato is a red fruit but considered a vegetable for culinary purposes. Its many varieties are now widely grown, sometimes in greenhouses in cooler climates. It should be planted in the end of spring and it will grow in about 3 months. Tomatoes need rich soil so you can previously treat yours with compost or similar. You have also to be careful with the cold since it can kill the plant. Tomatoes also need at least 6 hours of sun so make sure that your garden or terrace has plenty of sunlight. Regarding the watering, you should not water the plant’s leaves and water the plant not so regularly but with abundant water each time. You should also put a vertical stick attached so it grows properly.");
			p.setTimeToGrow(10);
			p.setWeatherId(1);

			tableExistingPlant.insert(p,
					new TableOperationCallback<ExistingPlant>() {

						public void onCompleted(ExistingPlant entity,
								Exception exception,
								ServiceFilterResponse response) {

							if (exception == null) {
								Log.d("TAG", "inserted");
							} else {
								Log.d("EXCEPTION", exception.toString());
								Log.d("TAG",
										" getCause = " + exception.getCause());
								Log.d("TAG",
										" getStackTrace = "
												+ exception.getStackTrace());
							}

						}
					});

			Weather w = new Weather();

			w.setWeatherId(6);
			w.setMinTemp(15);
			w.setMaxTemp(20);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(25000);
			w.setMaxLightness(75000);

			tableWeather.insert(w, new TableOperationCallback<Weather>() {

				public void onCompleted(Weather entity, Exception exception,
						ServiceFilterResponse response) {

					if (exception == null) {
						Log.d("TAG", "inserted");
					} else {
						Log.d("EXCEPTION", exception.toString());
						Log.d("TAG", " getCause = " + exception.getCause());
						Log.d("TAG",
								" getStackTrace = " + exception.getStackTrace());
					}

				}
			});

			w.setWeatherId(5);
			w.setMinTemp(15);
			w.setMaxTemp(20);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(25000);
			w.setMaxLightness(75000);

			tableWeather.insert(w, new TableOperationCallback<Weather>() {

				public void onCompleted(Weather entity, Exception exception,
						ServiceFilterResponse response) {

					if (exception == null) {
						Log.d("TAG", "inserted");
					} else {
						Log.d("EXCEPTION", exception.toString());
						Log.d("TAG", " getCause = " + exception.getCause());
						Log.d("TAG",
								" getStackTrace = " + exception.getStackTrace());
					}

				}
			});

			w.setWeatherId(4);
			w.setMinTemp(12);
			w.setMaxTemp(15);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(2500);
			w.setMaxLightness(75000);

			tableWeather.insert(w, new TableOperationCallback<Weather>() {

				public void onCompleted(Weather entity, Exception exception,
						ServiceFilterResponse response) {

					if (exception == null) {
						Log.d("TAG", "inserted");
					} else {
						Log.d("EXCEPTION", exception.toString());
						Log.d("TAG", " getCause = " + exception.getCause());
						Log.d("TAG",
								" getStackTrace = " + exception.getStackTrace());
					}

				}
			});

			w.setWeatherId(3);
			w.setMinTemp(16);
			w.setMaxTemp(18);
			w.setMinHumi(20);
			w.setMaxHumi(30);
			w.setMinLightness(2500);
			w.setMaxLightness(75000);

			tableWeather.insert(w, new TableOperationCallback<Weather>() {

				public void onCompleted(Weather entity, Exception exception,
						ServiceFilterResponse response) {

					if (exception == null) {
						Log.d("TAG", "inserted");
					} else {
						Log.d("EXCEPTION", exception.toString());
						Log.d("TAG", " getCause = " + exception.getCause());
						Log.d("TAG",
								" getStackTrace = " + exception.getStackTrace());
					}

				}
			});

			w.setWeatherId(2);
			w.setMinTemp(15);
			w.setMaxTemp(18);
			w.setMinHumi(10);
			w.setMaxHumi(20);
			w.setMinLightness(500);
			w.setMaxLightness(50000);

			tableWeather.insert(w, new TableOperationCallback<Weather>() {

				public void onCompleted(Weather entity, Exception exception,
						ServiceFilterResponse response) {

					if (exception == null) {
						Log.d("TAG", "inserted");
					} else {
						Log.d("EXCEPTION", exception.toString());
						Log.d("TAG", " getCause = " + exception.getCause());
						Log.d("TAG",
								" getStackTrace = " + exception.getStackTrace());
					}

				}
			});

			w.setWeatherId(1);
			w.setMinTemp(15);
			w.setMaxTemp(20);
			w.setMinHumi(40);
			w.setMaxHumi(60);
			w.setMinLightness(25000);
			w.setMaxLightness(75000);

			tableWeather.insert(w, new TableOperationCallback<Weather>() {

				public void onCompleted(Weather entity, Exception exception,
						ServiceFilterResponse response) {

					if (exception == null) {
						Log.d("TAG", "inserted");
					} else {
						Log.d("EXCEPTION", exception.toString());
						Log.d("TAG", " getCause = " + exception.getCause());
						Log.d("TAG",
								" getStackTrace = " + exception.getStackTrace());
					}

				}
			});

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

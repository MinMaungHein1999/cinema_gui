package com.cinema.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import com.cinema.dao.AbstractDao;

public abstract class BaseService<T> {
	protected static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AbstractDao<T> abstractDao;

	public abstract String getEntity();

	public abstract void register() throws IOException;

	public BaseService(AbstractDao<T> abstractDao) {
		this.abstractDao = abstractDao;
	}

	public void call() throws NumberFormatException, IOException {
		boolean exit = true;
		do {
			actionMenu();
			int choice = 5;
			try {
				choice = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				System.out.println("Error : " + e.getMessage());
				call();
			}

			switch (choice) {
			case 1:
				findById();
				break;
			case 2:
				getAll();
				break;
			case 3:
				register();
				break;
			case 4:
				destory();
				break;
			case 5:
				exit = false;
				System.out.println("Exiting the application...");
				break;
			}
		} while (exit);
	}

	public void actionMenu() {
		System.out.println("Choose an action:");
		System.out.println("1: Find " + getEntity() + " by ID");
		System.out.println("2: Get All " + getEntity() + "s");
		System.out.println("3: Create a New " + getEntity());
		System.out.println("4: Delete a " + getEntity());
		System.out.println("5: Exit");
	}

	private void destory() throws NumberFormatException, IOException {
		System.out.print("Enter " + getEntity() + " Id : ");
		int id = Integer.parseInt(br.readLine());
		this.abstractDao.delete(id);
	}

	public void getAll() {
		System.out.println("*** All " + getEntity() + "s ***");
		List<T> entities = this.abstractDao.getAll();
		for (T entity : entities) {
			System.out.println(entity);
		}
	}

	private void findById() {
		try {
			System.out.print("Enter " + getEntity() + " Id : ");
			int id = Integer.parseInt(br.readLine());
			T entity = (T) this.abstractDao.findbyId(id);
			System.out.println(entity);

		} catch (NumberFormatException | IOException e) {
			System.out.println("Please Enter Correct " + getEntity() + " Id !");
			findById();
		}
	}

	public AbstractDao<T> getAbstractDao() {
		return abstractDao;
	}

	public void setAbstractDao(AbstractDao<T> abstractDao) {
		this.abstractDao = abstractDao;
	}

}

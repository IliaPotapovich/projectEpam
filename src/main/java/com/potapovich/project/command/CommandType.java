package com.potapovich.project.command;

import com.potapovich.project.command.admin.find.admin.FindListOfAdminNameCommand;
import com.potapovich.project.command.admin.find.car.FindCarByIdCommand;
import com.potapovich.project.command.admin.find.car.FindCarByOwnerCommand;
import com.potapovich.project.command.admin.find.car.FindListOfDeletedCarsCommand;
import com.potapovich.project.command.admin.find.car.FindListOfTaxiCarCommand;
import com.potapovich.project.command.admin.find.driver.FindDriverByIdCommand;
import com.potapovich.project.command.admin.find.driver.FindDriverByNameCommand;
import com.potapovich.project.command.admin.find.driver.FindListOfDeletedDriversCommand;
import com.potapovich.project.command.admin.find.driver.FindListOfTaxiDriversCommand;
import com.potapovich.project.command.admin.find.taxi.FindTaxiByIdCommand;
import com.potapovich.project.command.admin.find.trip.FindListOfActiveTripsCommand;
import com.potapovich.project.command.admin.find.trip.FindListOfFinishedTripsCommand;
import com.potapovich.project.command.admin.find.trip.FindListOfTripsCommand;
import com.potapovich.project.command.admin.find.trip.FindTripByIdCommand;
import com.potapovich.project.command.admin.find.user.FindListOfUsersCommand;
import com.potapovich.project.command.admin.find.user.FindUserByIdCommand;
import com.potapovich.project.command.admin.find.user.FindUserByLoginCommand;
import com.potapovich.project.command.admin.restriction.*;
import com.potapovich.project.command.admin.registration.AdminRegCommand;
import com.potapovich.project.command.admin.registration.ChangeAdminPasswordCommand;
import com.potapovich.project.command.admin.registration.TaxiCarRegCommand;
import com.potapovich.project.command.admin.registration.TaxiDriverRegCommand;
import com.potapovich.project.command.taxi.*;
import com.potapovich.project.command.user.common.ShowListOfWorkingTaxiCommand;
import com.potapovich.project.command.user.common.order.ChooseAnotherTaxiCommand;
import com.potapovich.project.command.user.common.order.ConfirmTripCommand;
import com.potapovich.project.command.user.common.order.EvaluateTaxiWorkCommand;
import com.potapovich.project.command.user.common.order.TripEndCommand;
import com.potapovich.project.command.user.customer.login.EnterInUserRoomCommand;
import com.potapovich.project.command.user.customer.login.UserLoginCommand;
import com.potapovich.project.command.user.customer.login.UserRegistrCommand;
import com.potapovich.project.command.user.customer.order.ChooseTaxiForRegCommand;
import com.potapovich.project.command.user.customer.order.FindAppropListForRegCustomerCommand;
import com.potapovich.project.command.user.customer.room.ChangeUserPasswordCommand;
import com.potapovich.project.command.user.customer.room.FindHistoryOfUserTripsCommand;
import com.potapovich.project.command.user.guest.order.ChooseCertainTaxiCommand;
import com.potapovich.project.command.user.guest.order.FindListOfAppropriateTaxiCommand;
import com.potapovich.project.command.user.guest.order.QuickOrderTaxiCommand;
import com.potapovich.project.logic.AdminService;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.logic.TripService;
import com.potapovich.project.logic.UserService;

public enum CommandType {
    /**
     * List of Commands
     */
    LOGIN(new UserLoginCommand(new UserService())),
    FORWARD(new ForwardCommand()),
    ENTER_IN_USER_ROOM(new EnterInUserRoomCommand(new UserService())),
    LOGOUT(new LogoutCommand()),
    SET_LANGUAGE(new LanguageCommand()),
    USER_REGISTRATION(new UserRegistrCommand(new UserService())),
    TAXI_DRIVER_REGISTRATION(new TaxiDriverRegCommand(new TaxiService())),
    TAXI_CAR_REGISTRATION(new TaxiCarRegCommand(new TaxiService())),
    FIND_DRIVER_BY_ID(new FindDriverByIdCommand(new TaxiService())),
    FIND_DRIVER_BY_NAME(new FindDriverByNameCommand(new TaxiService())),
    FIND_CAR_BY_ID(new FindCarByIdCommand(new TaxiService())),
    FIND_CAR_BY_OWNER_ID(new FindCarByOwnerCommand(new TaxiService())),
    TECHNICAL_LOGIN(new TechnicalLoginCommand(new TaxiService(), new AdminService())),
    DRIVER_CHOOSES_CAR(new DriverChoosesCarCommand(new TaxiService())),
    ADMIN_REGISTRATION(new AdminRegCommand(new AdminService())),
    FIND_LIST_OF_ADMINS_NAME(new FindListOfAdminNameCommand(new AdminService())),
    FIND_LIST_OF_DRIVERS(new FindListOfTaxiDriversCommand(new TaxiService())),
    FIND_LIST_OF_CARS(new FindListOfTaxiCarCommand(new TaxiService())),
    SHOW_LIST_OF_WORKING_TAXI(new ShowListOfWorkingTaxiCommand(new TaxiService())),
    FINISH_WORK_DAY(new FinishWorkCommand(new TaxiService())),
    FIND_LIST_OF_APPROPRIATE_TAXI(new FindListOfAppropriateTaxiCommand(new TaxiService(), new UserService())),
    CHOOSE_TAXI(new ChooseCertainTaxiCommand(new TaxiService())),
    CONFIRM_TRIP(new ConfirmTripCommand(new TripService())),
    EVALUATE_TAXI_WORK(new EvaluateTaxiWorkCommand()),
    CONFIRM_TRIP_END(new TripEndCommand(new TripService(), new UserService())),
    CHOOSE_ANOTHER_TAXI(new ChooseAnotherTaxiCommand(new TaxiService())),
    QUICK_ORDER_TAXI(new QuickOrderTaxiCommand(new UserService(), new TaxiService())),
    FIND_APPROP_LIST_FOR_REG_CUSTOMER(new FindAppropListForRegCustomerCommand(new TaxiService(), new UserService())),
    CHOOSE_TAXI_FOR_REG(new ChooseTaxiForRegCommand(new TaxiService(), new UserService())),
    FIND_LIST_OF_USERS(new FindListOfUsersCommand(new UserService())),
    FIND_USER_BY_ID(new FindUserByIdCommand(new UserService())),
    FIND_USER_BY_LOGIN(new FindUserByLoginCommand(new UserService())),
    CHANGE_USER_LOCK_STATUS(new ChangeUserLockStatusCommand(new UserService())),
    CHANGE_DRIVER_LOCK_STATUS(new ChangeDriverLockStatusCommand(new TaxiService())),
    CHANGE_DRIVER_PASSWORD(new ChangeDriverPasswordCommand(new TaxiService())),
    CHANGE_USER_PASSWORD(new ChangeUserPasswordCommand(new UserService())),
    CHANGE_ADMIN_PASSWORD(new ChangeAdminPasswordCommand(new AdminService())),
    DELETE_DRIVER_BY_ID(new DeleteDriverByIdCommand(new TaxiService())),
    DELETE_CAR_BY_ID(new DeleteCarByIdCommand(new TaxiService())),
    FIND_HISTORY_OF_USERS_TRIPS(new FindHistoryOfUserTripsCommand(new TripService())),
    FIND_HISTORY_OF_DRIVER_TRIPS(new FindHistoryOfDriverTripsCommand(new TripService())),
    FIND_LIST_OF_TRIPS(new FindListOfTripsCommand(new TripService())),
    FIND_LIST_OF_ACTIVE_TRIPS(new FindListOfActiveTripsCommand(new TripService())),
    FIND_LIST_OF_FINISHED_TRIPS(new FindListOfFinishedTripsCommand(new TripService())),
    FIND_TRIP_BY_ID(new FindTripByIdCommand(new TripService())),
    FIND_TAXI_BY_ID(new FindTaxiByIdCommand(new TaxiService())),
    DOWNLOAD_AVATAR(new DownloadAvatarCommand(new TaxiService())),
    FIND_LIST_OF_DELETED_DRIVERS(new FindListOfDeletedDriversCommand(new TaxiService())),
    FIND_LIST_OF_DELETED_CARS(new FindListOfDeletedCarsCommand(new TaxiService())),
    ENTER_IN_TECHNICAL_ROOM(new EnterInTechnicalRoomCommand()),
    DELETE_PREVIOUS_DESIGN(new DeletePreviousSiteDesign()),
    SET_FREE_STATUS(new SetFreeWorkStatusCommand(new TaxiService(), new TripService()));

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}

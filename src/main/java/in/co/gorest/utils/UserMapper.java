package in.co.gorest.utils;

import in.co.gorest.models.User;
import in.co.gorest.models.UserModel;

public class UserMapper implements IMapper<User, UserModel> {

    @Override
    public UserModel map(User user) {
        UserModel userModel = new UserModel();
        User.Result result = user.getResult();

        userModel.setId(result.getId());
        userModel.setFirstName(result.getFirstName());
        userModel.setLastName(result.getLastName());
        userModel.setGender(result.getGender());
        userModel.setDob(result.getDob());
        userModel.setEmail(result.getEmail());
        userModel.setPhone(result.getPhone());
        userModel.setWebsite(result.getWebsite());
        userModel.setAddress(result.getAddress());
        userModel.setStatus(result.getStatus());
        userModel.setLinks(result.getLinks());

        return userModel;
    }
}
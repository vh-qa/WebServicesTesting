package in.co.gorest.utils;

public interface IMapper<From, To> {
    To map(From from);
}
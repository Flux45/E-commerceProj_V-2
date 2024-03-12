package com.scaler.productservicedecembermwf.Service;

import com.scaler.productservicedecembermwf.DTO.FakeStoreProductDTO;
import com.scaler.productservicedecembermwf.Exception.ProductNotExistException;
import com.scaler.productservicedecembermwf.Models.Category;
import com.scaler.productservicedecembermwf.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private Product convertFakeStoreDTOtoProduct (FakeStoreProductDTO fakeStoreProductDTO) {
        Product product = new Product();
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setId(fakeStoreProductDTO.getId());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setImageUrl(fakeStoreProductDTO.getImageURL());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDTO.getCategory());

        return product;
    }
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        FakeStoreProductDTO fakeStoreProductDTO =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + id,FakeStoreProductDTO.class);

        if (fakeStoreProductDTO == null) {
            throw new ProductNotExistException(
                    "Product with id: "+ id + " dosen't exist."
            );
        }
        return convertFakeStoreDTOtoProduct(fakeStoreProductDTO);
    }

    @Override
    public Optional<List<Product>> getAllProducts() {
        return Optional.empty();
    }


//    @Override
//    public <List<Product>> getAllProducts() {
//        FakeStoreProductDTO[] response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
//
//        List<Product> answer = new ArrayList<>();
//
//        for(FakeStoreProductDTO dto: response) {
//            answer.add(convertFakeStoreDTOtoProduct(dto));
//        }
//        return answer;
//    }

    @Override
    public Product addANewProduct(Product product) {
        return product;
    }

    @Override
    public Product updateAProduct(Long id,Product product) {

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setImageURL(product.getImageUrl());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDTO, FakeStoreProductDTO.class);
        HttpMessageConverterExtractor<FakeStoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDTO.class, restTemplate.getMessageConverters());
        FakeStoreProductDTO response = restTemplate.execute
                ("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreDTOtoProduct(response);
    }

    @Override
    public List<Product> specificCategory(String ct) {
        return null;
    }

//    @Override
//    public Optional<Category> specificCategory(String ct) {
//        return Optional.empty();
//    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

//    @Override
//    public List<String> allCategories() {
//        String[] response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/categories", String[].class);
//
//        List<String> categories = new ArrayList<>();
//
//        for(String c: response) {
//            categories.add(c);
//        }
//
//        return categories;
//    }

//    @Override
//    public List<Product> specificCategory(String ct) {
//        FakeStoreProductDTO[] response = restTemplate.getForObject(
//                "https://fakestoreapi.com/products/category/" + ct, FakeStoreProductDTO[].class);
//        List<Product> answer = new ArrayList<>();
//        for(FakeStoreProductDTO dto: response) {
//            answer.add(convertFakeStoreDTOtoProduct(dto));
//        }
//
//        return answer;
//    }

    @Override
    public Optional<List<Category>> allCategory() {
        return Optional.empty();
    }

}

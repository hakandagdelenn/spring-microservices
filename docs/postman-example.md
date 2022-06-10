##host : http://localhost:8502

##swagger : http://localhost:8502/swagger-ui/

## Postman documentation url : https://documenter.getpostman.com/view/20537549/Uyr4M1DJ

## Create New Bag 
```
POST http://localhost:8502/api/v1/bag/create

{
    "barcode": "string",
    "deliveryPointId": 0
}
```
## Assign Package to Bag

```
POST http://localhost:8502/api/v1/bag/assign-packages-to-bag

{
    "bagBarcode": "string",
    "packageBarcodes": [
        "string"
    ]
}

```
## Create New Packages
```
POST http://localhost:8502/api/v1/package/create

{
    "barcode": "string",
    "deliveryPointId": 0,
    "volumetricWeight": 0
}

```

## Create New Delivery Point
```
POST http://localhost:8502/api/v1/delivery-point/create

{
    "deliveryPointType": "string",
    "name": "string"
}

```

## Create New Vehicle
```
POST http://localhost:8502/api/v1/vehicle/create

{
    "plate": "string"
}

```

## Assign Shipments to Vehicle
```
POST http://localhost:8502/api/v1/vehicle/assign-shipment-to-vehicle

{
    "plate": "string",
    "route": [
        {
            "deliveries": [
                {
                    "barcode": "string"
                }
            ],
            "deliveryPoint": 0
        }
    ]
}

```
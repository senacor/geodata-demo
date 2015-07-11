describe('PostalcodeLookupCtrl', function () {
    beforeEach(module('angularjsApp'));

    var $controller, $scope;

    beforeEach(inject(function (_$controller_, $rootScope) {
        // The injector unwraps the underscores (_) from around the parameter names when matching
        $controller = _$controller_;
        $scope = $rootScope.$new();
    }));

    describe('Controller and functions', function () {
        var controller;

        beforeEach(function () {
            controller = $controller('PostalcodeLookupCtrl', {$scope: $scope});
        });

        it('is available', function () {
            expect(controller).toBeDefined();
        });

        it('defines the seach method', function () {
            expect($scope.search).toBeDefined();
        });
    });

    describe('the httpBackend returns a valid searchresuult', function () {
        var controller, $httpBackend, expectedPostalcodes;
        expectedPostalcodes = [
            {
                "postalCode": "45659",
                "placeName": "Recklinghausen"
            }
        ];
        beforeEach(inject(function (_$httpBackend_) {
            $httpBackend = _$httpBackend_;
            $httpBackend.when('GET', 'http://api.geonames.org/findNearbyPostalCodesJSON?country=DE&radius=10&username=myGeo&postalcode=45659')
                .respond({
                    "postalCodes": expectedPostalcodes
                }
            );
            controller = $controller('PostalcodeLookupCtrl', {$scope: $scope});
        }));

        it('should the set result to the sope after the variables are set and search was called"', function () {
            $scope.country = {"code": "DE"};
            $scope.postalCode = "45659";
            $scope.search();
            $httpBackend.flush();
            expect($scope.codes).toEqual(expectedPostalcodes);
        });
    });


});